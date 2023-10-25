package com.szml.pl.controller;

import com.alibaba.fastjson.JSON;
import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@RestController
@RequestMapping("/manage")
public class ProductManageController {

    @Resource
    private ProductService productService;

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('test','all')")
    public String test(){
        return "测试系统";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('all','edit')")
    public String edit(){
        return "编辑权限";
    }
    /**
     * 商品查询（条件查询）
     */
    public Result queryProductList(){
        return null;
    }

    /**
     * 单一商品查询
     */
    public Result queryProduct(@RequestParam("productId") Long productId){
        return null;
    }

    /**
     * 管理人权限移交
     */
    @PostMapping("/updateManager")
    public Result updateManager(@RequestParam("productId") Long productId,@RequestParam("manager") String manager){
        return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productService.updateManager(productId,manager)));
    }
}
