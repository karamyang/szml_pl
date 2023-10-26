package com.szml.pl.controller;

import com.alibaba.fastjson.JSON;
import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.service.ProductCategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @description: 商品类型查询
 * @author：wufengning
 * @date: 2023/10/22
 */
@RestController
@RequestMapping("/category")
public class ProductCategoryController {
    @Resource
    private ProductCategoryService categoryService;

    /**
     *
     * @return
     */
    @GetMapping("/querylist")
    @PreAuthorize("hasAnyAuthority('edit')")
    public Result queryCategoryList(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(categoryService.queryCategoryList()));
    }
}
