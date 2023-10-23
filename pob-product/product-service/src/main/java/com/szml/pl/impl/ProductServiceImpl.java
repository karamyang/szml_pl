package com.szml.pl.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.dao.ProductDraftDao;
import com.szml.pl.dao.ProductRecordDao;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.entity.ProductRecord;
import com.szml.pl.service.ProductRecordService;
import com.szml.pl.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

import javax.annotation.Resource;

/**
 * @description: 商品服务
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Resource
    ProductDao productDao;
    @Resource
    private ProductDraftDao productDraftDao;
    @Resource
    private ProductRecordService productRecordService;

    @Resource
    private ProductService productService;
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
            product.setId(null);
            int flag1 = productDao.insert(product);
            if(flag<0||flag1<0) return false;
        }
        else {
            //2.判断是否存在于商品表
            Product product1 = productDao.selectById(productId);
            //存在则更新商品
            if(product1!=null){
                //更新商品
                product.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                int flag = productDao.updateById(product);
                return flag>0?true:false;
            }
            //3.否则添加商品到商品表
            product.setCreateTime(new Timestamp(System.currentTimeMillis()));
            int flag = productDao.insert(product);
            return flag>0?true:false;
        }
        //4.增加操作表记录
        productRecordService.addRecord(product,Constants.ProductRecordState.SUBMITDRAFT.getCode(),Constants.ProductRecordState.SUBMITDRAFT.getInfo());
        return true;
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
        Integer addRecord = productRecordService.addRecord(product1, Constants.ProductRecordState.NOPASSREVIEW.getCode(), Constants.ProductRecordState.NOPASSREVIEW.getInfo());
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
        Integer addRecord = productRecordService.addRecord(product1,  Constants.ProductRecordState.PASSREVIEW.getCode(), Constants.ProductRecordState.PASSREVIEW.getInfo());
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
    @Transactional
    public Result submit(ProductDto product) {
       return productService.commitProduct(product)? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
               Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }


}
