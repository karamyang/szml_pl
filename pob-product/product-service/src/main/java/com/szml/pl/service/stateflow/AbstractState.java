package com.szml.pl.service.stateflow;

import com.szml.pl.common.Result;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.ProductService;

import javax.annotation.Resource;

/**
 * @description: 商品状态抽象类
 * @author：karma
 * @date: 2023/10/22
 */
public abstract class AbstractState {


    @Resource
    protected ProductService productService;

    //发起审核
    public abstract Result audit(Product product, Integer currentStatus);
    //编辑保存
    public abstract Result submit(Product product, Integer currentStatus);
    //审核通过
    public abstract Result pass(Product product, Integer currentStatus);
    //审核驳回
    public abstract Result nopass(Product product, Integer currentStatus);
    //上线
    public abstract Result online(Product product, Integer currentStatus);
    //下线
    public abstract Result offline(Product product, Integer currentStatus);
    //编辑
    public abstract Result compile(Product product, Integer currentStatus);
}
