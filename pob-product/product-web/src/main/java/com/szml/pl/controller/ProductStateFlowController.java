package com.szml.pl.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.impl.ProductDraftServiceImpl;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.ProductService;
import com.szml.pl.service.stateflow.IStateHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description: 商品状态变化流程
 * @author：karma
 * @date: 2023/10/21
 */
@RestController
@RequestMapping("/stateflow")
public class ProductStateFlowController {

    @Resource
    ProductService productService;
    @Resource
    IStateHandler stateHandler;
    /**
     * 发起审核
     */
    @PostMapping(value = "/audit")
    public Result audit(@RequestBody ProductDto productDto) {
        return stateHandler.audit(productDto,productDto.getStatus());
    }
    /**
     * 提交
     */
    @PostMapping(value = "/submit")
    public Result submit(@RequestBody ProductDto productDto) {
        return stateHandler.submit(productDto,productDto.getStatus());
    }

    /**
     * 审核驳回
     */
    @PostMapping(value = "/nopass")
    public Result nopass(@RequestBody ProductDto productDto) {
        return stateHandler.nopass(productDto,productDto.getStatus());
    }

    /**
     * 审核通过
     */
    @PostMapping(value = "/pass")
    public Result pass(@RequestBody ProductDto productDto) {
        return stateHandler.pass(productDto,productDto.getStatus());
    }

    /**
     * 上线
     */
    @PostMapping(value = "/online")
    public Result online(@RequestBody ProductDto productDto) {
        System.out.println(productDto.toString());
        return stateHandler.online(productDto,productDto.getStatus());
    }

    /**
     * 下线
     */
    @PostMapping(value = "/offline")
    public Result offline(@RequestBody ProductDto productDto) {
        return stateHandler.offline(productDto,productDto.getStatus());
    }
    /**
     * 编辑
     */
    @PostMapping(value = "/compile")
    public Result compile(@RequestBody ProductDto productDto) {
        System.out.println(productDto.toString());
        return stateHandler.compile(productDto,productDto.getStatus());
    }

    /**
     * 批量操作
     */
    @PostMapping(value = "/batchoperation")
    public Result batchoperation(@RequestBody List<Product> productList,Integer operation) {
        return productService.batchoperation(productList,operation);
    }

    /**
     * 库存为0下线
     */
    @PostMapping(value = "/stockoffline")
    public Result stockoffline() {
        return productService.stockoffline();
    }
}
