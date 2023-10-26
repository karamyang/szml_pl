package com.szml.pl.service.stateflow.event;

import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.dto.ProductDto;
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
    public Result audit(ProductDto productDto, Integer currentStatus) {
        return productService.audit(productDto);
    }

    @Override
    public Result submit(ProductDto productDto, Integer currentStatus) {
        return productService.submit(productDto);
    }

    @Override
    public Result pass(ProductDto productDto, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }

    @Override
    public Result nopass(ProductDto productDto, Integer currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
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
        Boolean aBoolean = productDraftService.saveDraft(productDto);
        return aBoolean ? Result.buildResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo()) :
                Result.buildResult(Constants.ResponseCode.UN_ERROR.getCode(),Constants.ResponseCode.UN_ERROR.getInfo());
    }
}
