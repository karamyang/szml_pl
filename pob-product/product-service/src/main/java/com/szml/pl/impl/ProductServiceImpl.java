package com.szml.pl.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.common.ids.IIdGenerator;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.dao.ProductDraftDao;
import com.szml.pl.dao.ProductRecordDao;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.entity.ProductRecord;
import com.szml.pl.service.ProductRecordService;
import com.szml.pl.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

/**
 * @description: 商品服务
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Resource
    ProductDao productDao;
    @Resource
    private ProductDraftDao productDraftDao;
    @Resource
    private ProductRecordService productRecordService;

    @Resource
    private ProductService productService;
    @Resource
    IIdGenerator idGenerator;
    @Override
    @Transactional
    public Boolean commitProduct(ProductDto productDto) {
        //1.转化数据
        Long productId=productDto.getId();
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        product.setStatus(Constants.ProductState.UNREVIEW.getCode());
        //1.判断草稿表中是否存在
        ProductDraft productDraft = productDraftDao.selectById(productId);
        if(productDraft!=null){
            //删除草稿表中的内容
            int flag = productDraftDao.deleteById(productId);
            //从草稿插入到商品
            product.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //防止草稿表中的id和商品表中id冲突
            //用雪花算法设置全局id
            product.setId(idGenerator.nextId());
            int flag1 = productDao.insert(product);
            if(flag<0||flag1<0) {
                return false;
            }
        }
        else {
            //2.判断是否存在于商品表
            Product product1 = productDao.selectById(productId);
            //存在则更新商品
            if(product1!=null){
                //更新商品
                product.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                int flag = productDao.updateById(product);
                if(flag>0){
                    //4.增加操作表记录
                    productRecordService.addRecord(product,Constants.ProductRecordState.UPDATE.getCode(),Constants.ProductRecordState.UPDATE.getInfo());
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                //3.否则添加商品到商品表
                product.setCreateTime(new Timestamp(System.currentTimeMillis()));
                //用雪花算法设置全局id
                product.setId(idGenerator.nextId());
                int flag = productDao.insert(product);
                if(flag<0){
                    return false;
                }
            }
        }
        //4.增加操作表记录
        productRecordService.addRecord(product,Constants.ProductRecordState.SUBMITDRAFT.getCode(),Constants.ProductRecordState.SUBMITDRAFT.getInfo());
        return true;
    }

    @Override
    @Transactional
    public Result batchoperation(List<Product> productList, Integer operation) {
        int record;
        Iterator<Product> iterator = productList.iterator();
        boolean b;
        if(operation.equals(Constants.ProductRecordState.ONLINE.getCode())){
            record=Constants.ProductState.ONLINE.getCode();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getStatus().equals(Constants.ProductState.OFFLINE.getCode()) ||
                        product.getStatus().equals(Constants.ProductState.PASSREVIEW.getCode())) {
                    product.setStatus(record);
                }else {
                    iterator.remove();//使用迭代器的删除方法删除
                }
            }
            b=productService.updateBatchById(productList);
        }else {
            record=Constants.ProductState.OFFLINE.getCode();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getStatus().equals(Constants.ProductState.ONLINE.getCode())) {
                    product.setStatus(record);
                }else {
                    iterator.remove();//使用迭代器的删除方法删除
                }
            }
            b=productService.updateBatchById(productList);
        }
        return b ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }


    /**
     * 提交审核
     * @param product
     * @return
     */
    @Override
    @Transactional
    public Result audit(ProductDto product) {
        Product product1 = productDao.selectById(product.getId());
        if(product1==null){
            Result.buildResult(Constants.ResponseCode.NO_ADMIN.getCode(),Constants.ResponseCode.NO_ADMIN.getInfo());
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.UNDERREVIEW.getCode());
        updateWrapper.set("update_time", new Timestamp(System.currentTimeMillis()));
        int update = productDao.update(product1, updateWrapper);
        Integer addRecord = productRecordService.addRecord(product1, Constants.ProductRecordState.REVIEW.getCode(), Constants.ProductRecordState.REVIEW.getInfo());
        return update>0&&addRecord>0 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    /**
     * 审核驳回
     * @param product
     * @return
     */
    @Override
    @Transactional
    public Result nopass(ProductDto product) {
        Product product1 = productDao.selectById(product.getId());
        if(product1==null){
            Result.buildResult(Constants.ResponseCode.NO_ADMIN.getCode(),Constants.ResponseCode.NO_ADMIN.getInfo());
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.UNREVIEW.getCode());
        updateWrapper.set("update_time", new Timestamp(System.currentTimeMillis()));
        int update = productDao.update(product1, updateWrapper);
        Integer addRecord = productRecordService.addRecord(product1, Constants.ProductRecordState.NOPASSREVIEW.getCode(), product.getRemark());
        return update>0&&addRecord>0 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    /**
     * 审核通过
     * @param product
     * @return
     */
    @Override
    @Transactional
    public Result pass(ProductDto product) {
        Product product1 = productDao.selectById(product.getId());
        if(product1==null){
            Result.buildResult(Constants.ResponseCode.NO_ADMIN.getCode(),Constants.ResponseCode.NO_ADMIN.getInfo());
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.PASSREVIEW.getCode());
        updateWrapper.set("update_time", new Timestamp(System.currentTimeMillis()));
        int update = productDao.update(product1, updateWrapper);
        Integer addRecord = productRecordService.addRecord(product1,  Constants.ProductRecordState.PASSREVIEW.getCode(),product.getRemark());
        return update>0&&addRecord>0 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    /**
     * 上线
     * @param product
     * @return
     */
    @Override
    @Transactional
    public Result online(ProductDto product) {
        Product product1 = productDao.selectById(product.getId());
        if(product1==null){
            Result.buildResult(Constants.ResponseCode.NO_ADMIN.getCode(),Constants.ResponseCode.NO_ADMIN.getInfo());
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.ONLINE.getCode());
        updateWrapper.set("online_time", new Timestamp(System.currentTimeMillis()));
        updateWrapper.set("update_time", new Timestamp(System.currentTimeMillis()));
        int update = productDao.update(product1, updateWrapper);
        Integer addRecord = productRecordService.addRecord(product1,  Constants.ProductRecordState.ONLINE.getCode(), Constants.ProductRecordState.ONLINE.getInfo());
        return update>0&&addRecord>0 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    /**
     * 商品下线
     * @param product
     * @return
     */
    @Override
    @Transactional
    public Result offline(ProductDto product) {
        Product product1 = productDao.selectById(product.getId());
        if(product1==null){
            Result.buildResult(Constants.ResponseCode.NO_ADMIN.getCode(),Constants.ResponseCode.NO_ADMIN.getInfo());
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.OFFLINE.getCode());
        updateWrapper.set("line_time", new Timestamp(System.currentTimeMillis()));
        updateWrapper.set("update_time", new Timestamp(System.currentTimeMillis()));
        int update = productDao.update(product1, updateWrapper);
        Integer addRecord = productRecordService.addRecord(product1,  Constants.ProductRecordState.OFFLINE.getCode(), Constants.ProductRecordState.OFFLINE.getInfo());
        return update>0&&addRecord>0 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }
    @Override
    @Scheduled(cron = "0 0 10,14,18 * * ?")
    public Result stockoffline() {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("status",Constants.ProductState.ONLINE.getCode());
        updateWrapper.eq("stock",0);
        updateWrapper.set("status", Constants.ProductState.OFFLINE.getCode());
        updateWrapper.set("line_time", new Timestamp(System.currentTimeMillis()));
        updateWrapper.set("update_time", new Timestamp(System.currentTimeMillis()));
        return productService.update(updateWrapper) ?  Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }
    @Override
    public Result submit(ProductDto product) {
       return productService.commitProduct(product)? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
               Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }


    @Override
    public Product findProductById(Long id){
        return productDao.selectProductById(id);
    }
    @Override
    public List<ProductDto> findProductFromUser(String rightId, String productName,
                                                Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productDao.selectProductFromUser(rightId,productName,onlineTime,lineTime,status,manageUserId);
        for(Product product:products) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }
    @Override
    public List<ProductDto> findProductFromAdmin(String rightId, String productName,
                                                 Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productDao.selectProductFromAdmin(rightId,productName,onlineTime,lineTime,status,manageUserId);
        for(Product product:products) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public List<ProductDto> findProductAndProductAgentFromUser(String rightId, String productName,
                                                               Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId,Long adminId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productDao.selectProductAndProductAgentFromUser(rightId,productName,onlineTime,lineTime,status,manageUserId,adminId);
        for(Product product:products) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }
    @Override
    public List<ProductDto> findProductAndProductAgentFromAdmin(String rightId, String productName,
                                                                Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId,Long adminId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productDao.selectProductAndProductAgentFromAdmin(rightId,productName,onlineTime,lineTime,status,manageUserId,adminId);
        for(Product product:products) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }



}
