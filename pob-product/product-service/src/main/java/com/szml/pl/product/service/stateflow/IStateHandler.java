package com.szml.pl.product.service.stateflow;


import com.szml.pl.common.Result;
import com.szml.pl.product.model.Product;
import com.szml.pl.product.model.ProductDraft;

public interface IStateHandler {

    //发起审核
    Result submit(ProductDraft productDraft, Integer currentStatus);
    //编辑保存
    Result save(ProductDraft productDraft, Integer currentStatus);
    //审核通过
    Result pass(Product product, Integer currentStatus);
    //审核驳回
    Result nopass(Product product, Integer currentStatus);
    //上线
    Result online(Product product, Integer currentStatus);
    //下线
    Result offline(Product product, Integer currentStatus);
}
