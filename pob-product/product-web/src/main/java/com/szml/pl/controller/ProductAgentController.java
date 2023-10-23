package com.szml.pl.controller;

import com.alibaba.fastjson.JSON;
import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.service.ProductAgentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@RestController
@RequestMapping("/agent")
public class ProductAgentController {

    @Resource
    private ProductAgentService productAgentService;

    /**
     * 查询目标商品所有代理人
     */
    @GetMapping("/querylist")
    public Result queryAgent(@RequestParam("productId") Long productId){
        return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productAgentService.queryListByProductId(productId)));
    }

    /**
     * 代理人存在一部分功能没有开发
     */
}
