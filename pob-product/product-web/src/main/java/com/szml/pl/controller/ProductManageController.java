package com.szml.pl.controller;

import com.szml.pl.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@RestController
@RequestMapping("/manage")
public class ProductManageController {
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
}
