package com.szml.pl.service.stateflow.impl;

import com.szml.pl.common.response.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.service.stateflow.IStateHandler;
import com.szml.pl.service.stateflow.StateConfig;
import org.springframework.stereotype.Service;

/**
 * @description: 商品流程的实现类
 * @author：karma
 * @date: 2023/10/22
 */
@Service
public class StateHandlerImpl extends StateConfig implements IStateHandler {

    @Override
    public Result audit(ProductDto productDto, Integer currentStatus) {

        return stateGroup.get(currentStatus).audit(productDto,currentStatus);
    }

    @Override
    public Result submit(ProductDto productDto, Integer currentStatus) {
        return stateGroup.get(currentStatus).submit(productDto,currentStatus);
    }

    @Override
    public Result pass(ProductDto productDto, Integer currentStatus) {
        return stateGroup.get(currentStatus).pass(productDto,currentStatus);
    }

    @Override
    public Result nopass(ProductDto productDto, Integer currentStatus) {
        return stateGroup.get(currentStatus).nopass(productDto,currentStatus);
    }

    @Override
    public Result online(ProductDto productDto, Integer currentStatus) {
        return stateGroup.get(currentStatus).online(productDto,currentStatus);
    }

    @Override
    public Result offline(ProductDto productDto, Integer currentStatus) {
        return stateGroup.get(currentStatus).offline(productDto,currentStatus);
    }

    @Override
    public Result compile(ProductDto productDto, Integer currentStatus) {
        return stateGroup.get(currentStatus).compile(productDto,currentStatus);
    }
}
