package com.szml.pl.service.stateflow.event;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @description: 未审核状态
 * @author：karma
 * @date: 2023/10/22
 */
@Component
public class UnReviewedState extends AbstractState {
    @Override
    public Result audit(Product product, Integer currentStatus) {
        return productService.audit(product);
    }

    @Override
    public Result submit(Product product, Integer currentStatus) {
        return productService.submit(product);
    }

    @Override
    public Result pass(Product product, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result nopass(Product product, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result online(Product product, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result offline(Product product, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result compile(Product product, Integer currentStatus) {
        return productService.compile(product);
    }
}
