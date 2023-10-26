package com.szml.pl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

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
    @Resource
    private ProductDraftService productDraftService;
    /**
     * 商品查询（条件查询）
     */
    @RequestMapping(value = "/gets",method = RequestMethod.POST)
    public Result queryProductList(@RequestBody ProductDto productDto,@RequestParam Long current,@RequestParam Long size){
        //current为当前页数  size为每页数量

        if(productDto.getAdminId()==null){//代理人为空 查询商品和草稿

            //普通用户查找商品表（不包括审核中的商品）
            Page<ProductDto> productDtoPage=productService.findProductFromUser(productDto,current,size);
            //管理员查找商品表
//          Page<ProductDto> productDtoPage=productService.findProductFromAdmin(productDto,current,size);
            //普通用户查找草稿表（不包括审核中的商品）
            Page<ProductDto> productDtoPage1=productDraftService.findProductDraftFromUser(productDto,current,size);
            //管理员查找草稿表
//          Page<ProductDto> productDtoPage1=productDraftService.findProductProductDraftFromAdmin(productDto,current,size);

            return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productDtoPage));
        }
        else{//存在代理人为 查找商品和代理

            //普通用户查找
            Page<ProductDto> productDtoPage=productService.findProductAndProductAgentFromUser(productDto,current,size);
            //管理员查找
//          Page<ProductDto> productDtoPage=productService.findProductAndProductAgentFromAdmin(productDto,current,size);

            return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productDtoPage));
        }
    }

    /**
     * 单一商品查询(商品详情)
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Result queryProduct(@RequestParam("productId") Long productId){
        Product product=productService.findProductById(productId);
        if(product == null){
            return Result.buildErrorResult();
        }

        if(!product.getStatus().equals(1)){//当前商品不是草稿 跳转到商品详情


            return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(product));
        }
        else if( true ){//判断当前用户是否具有编辑草稿权限

        }
        else {

        }

        return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(product));
    }
}
