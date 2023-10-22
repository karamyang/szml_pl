package com.szml.pl.service.stateflow;


import com.szml.pl.common.Result;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;

/**
 * @description: 状态处理接口
 * @author：karma
 * @date: 2023/10/22
 */
public interface StateHandler {
    //提交审核
    Result audit(Product product, Integer currentStatus);
    //提交
    Result submit(Product product, Integer currentStatus);
    //审核通过
    Result pass(Product product, Integer currentStatus);
    //审核驳回
    Result nopass(Product product, Integer currentStatus);
    //上线
    Result online(Product product, Integer currentStatus);
    //下线
    Result offline(Product product, Integer currentStatus);
    //编辑
    Result compile(Product product, Integer currentStatus);
}
