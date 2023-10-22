package com.szml.pl.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.entity.Product;
import com.szml.pl.service.ProductService;
import org.springframework.stereotype.Service;

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

    @Override
    public Result audit(Product product) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.UNDERREVIEW.getCode());
        int update = productDao.update(product, updateWrapper);
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result nopass(Product product) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.UNREVIEW.getCode());
        int update = productDao.update(product, updateWrapper);
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result pass(Product product) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.PASSREVIEW.getCode());
        int update = productDao.update(product, updateWrapper);
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result online(Product product) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.ONLINE.getCode());
        int update = productDao.update(product, updateWrapper);
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result offline(Product product) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",product.getId());
        updateWrapper.set("status", Constants.ProductState.OFFLINE.getCode());
        int update = productDao.update(product, updateWrapper);
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result submit(Product product) {
        product.setStatus(Constants.ProductState.UNREVIEW.getCode());
        int update = productDao.updateById(product);
        return update==1 ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result compile(Product product) {
        return null;
    }
}
