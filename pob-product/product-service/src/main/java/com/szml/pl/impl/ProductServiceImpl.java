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
    @Override
    @Transactional
    public Boolean commitProduct(ProductDto productDto) {
        //1.转化数据
        Long productId=productDto.getId();
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        product.setStatus(2);
        //1.判断草稿表中是否存在
        ProductDraft productDraft = productDraftDao.selectById(productId);
        if(productDraft!=null){
            //删除草稿表中的内容
            productDraftDao.deleteById(productId);
        }
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
        //4.增加操作表记录
        productRecordService.addRecord(product,1L,"新增商品");
        return flag>0?true:false;
    }
    @Override
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
        return update>0 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
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
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
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
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
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
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
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
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result submit(ProductDto product) {
        Product product1 = productDao.selectById(product.getId());
        if(product1==null){
            Result.buildResult(Constants.ResponseCode.NO_ADMIN.getCode(),Constants.ResponseCode.NO_ADMIN.getInfo());
        }
        product1.setStatus(Constants.ProductState.UNREVIEW.getCode());
        product1.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int update = productDao.updateById(product1);
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }


}
