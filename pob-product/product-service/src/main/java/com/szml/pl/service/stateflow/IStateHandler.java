package com.szml.pl.service.stateflow;


import com.szml.pl.common.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;

/**
 * @description: 状态处理接口
 * @author：karma
 * @date: 2023/10/22
 */
public interface IStateHandler {
    //提交审核
    Result audit(ProductDto productDto, Integer currentStatus);
    //提交
    Result submit(ProductDto productDto, Integer currentStatus);
    //审核通过
    Result pass(ProductDto productDto, Integer currentStatus);
    //审核驳回
    Result nopass(ProductDto productDto, Integer currentStatus);
    //上线
    Result online(ProductDto productDto, Integer currentStatus);
    //下线
    Result offline(ProductDto productDto, Integer currentStatus);
    //编辑
    Result compile(ProductDto productDto, Integer currentStatus);
}
