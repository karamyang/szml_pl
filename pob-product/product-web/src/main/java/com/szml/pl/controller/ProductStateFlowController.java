package com.szml.pl.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.impl.ProductDraftServiceImpl;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.stateflow.StateHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @description: 商品状态变化流程
 * @author：karma
 * @date: 2023/10/21
 */
@RestController
@RequestMapping("/stateflow")
public class ProductStateFlowController {

    @Resource
    ProductDraftService productDraftService;
    @Resource
    StateHandler stateHandler;
    //发起审核
    @PostMapping(value = "/audit")
    public Result audit(@RequestBody Product product) {;
        return stateHandler.audit(product,product.getStatus());
    }
    //编辑保存
    @PostMapping(value = "/submit")
    public Result submit(@RequestBody Product product) {
        return stateHandler.submit(product,product.getStatus());
    }

    //审核驳回
    @PostMapping(value = "/nopass")
    public Result nopass(@RequestBody Product product) {
        return stateHandler.nopass(product,product.getStatus());
    }

    //审核通过
    @PostMapping(value = "/pass")
    public Result pass(@RequestBody Product product) {
        return stateHandler.pass(product,product.getStatus());
    }

    //上线
    @PostMapping(value = "/online")
    public Result online(@RequestBody Product product) {
        return stateHandler.online(product,product.getStatus());
    }

    //下线
    @PostMapping(value = "/offline")
    public Result offline(@RequestBody Product product) {
        return stateHandler.offline(product,product.getStatus());
    }
    //编辑
    @PostMapping(value = "/compile")
    public Result compile(@RequestBody Product product) {;
        return stateHandler.compile(product,product.getStatus());
    }
}
