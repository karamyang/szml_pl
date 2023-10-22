package com.szml.pl.service.stateflow.impl;

import com.szml.pl.common.Result;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.stateflow.StateConfig;
import com.szml.pl.service.stateflow.StateHandler;
import org.springframework.stereotype.Service;

/**
 * @description: 商品流程的实现类
 * @author：karma
 * @date: 2023/10/22
 */
@Service
public class StateHandlerImpl extends StateConfig implements StateHandler {
    @Override
    public Result audit(Product product, Integer currentStatus) {
        return stateGroup.get(currentStatus).audit(product,currentStatus);
    }

    @Override
    public Result submit(Product product, Integer currentStatus) {
        return stateGroup.get(currentStatus).submit(product,currentStatus);
    }

    @Override
    public Result pass(Product product, Integer currentStatus) {
        return stateGroup.get(currentStatus).pass(product,currentStatus);
    }

    @Override
    public Result nopass(Product product, Integer currentStatus) {
        return stateGroup.get(currentStatus).nopass(product,currentStatus);
    }

    @Override
    public Result online(Product product, Integer currentStatus) {
        return stateGroup.get(currentStatus).online(product,currentStatus);
    }

    @Override
    public Result offline(Product product, Integer currentStatus) {
        return stateGroup.get(currentStatus).offline(product,currentStatus);
    }

    @Override
    public Result compile(Product product, Integer currentStatus) {
        return stateGroup.get(currentStatus).compile(product,currentStatus);
    }
}
