package com.szml.pl.service.stateflow;

import com.szml.pl.common.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.ProductDraftService;
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
    @Resource
    protected ProductDraftService productDraftService;

    //发起审核
    public abstract Result audit(ProductDto productDto, Integer currentStatus);
    //编辑保存
    public abstract Result submit(ProductDto productDto, Integer currentStatus);
    //审核通过
    public abstract Result pass(ProductDto productDto, Integer currentStatus);
    //审核驳回
    public abstract Result nopass(ProductDto productDto, Integer currentStatus);
    //上线
    public abstract Result online(ProductDto productDto, Integer currentStatus);
    //下线
    public abstract Result offline(ProductDto productDto, Integer currentStatus);
    //编辑
    public abstract Result compile(ProductDto productDto, Integer currentStatus);
}
