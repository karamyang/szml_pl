package com.szml.pl.service.stateflow.event;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @description: 审核中状态
 * @author：karma
 * @date: 2023/10/22
 */
@Component
public class UnderReviewState extends AbstractState {
    @Override
    public Result audit(ProductDto productDto, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result submit(ProductDto productDto, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result pass(ProductDto productDto, Integer currentStatus) {
        return productService.pass(productDto);
    }

    @Override
    public Result nopass(ProductDto productDto, Integer currentStatus) {
        return  productService.nopass(productDto);
    }

    @Override
    public Result online(ProductDto productDto, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result offline(ProductDto productDto, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result compile(ProductDto productDto, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }
}
