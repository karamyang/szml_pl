package com.szml.pl.controller;

import com.alibaba.fastjson.JSON;
import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public Result queryProductList(@RequestBody ProductDto productDto){

        String rightId= productDto.getRightId();
        String productName = productDto.getProductName();
        Timestamp onlineTime = productDto.getOnlineTime();
        Timestamp lineTime = productDto.getLineTime();
        Integer status = productDto.getStatus();
        Long manageUserId = productDto.getManageUserId();
        Long adminId=productDto.getAdminId();

        if(adminId==null){//代理人为空 查询商品和草稿

            //普通用户查找商品表（不包括审核中的商品）
            List<ProductDto> productDtoList=productService.findProductFromUser(rightId,productName,onlineTime,lineTime,status,manageUserId);
            //管理员查找商品表
//          List<ProductDto> productDtoList=productService.findProductFromAdmin(rightId,productName,onlineTime,lineTime,status,manageUserId);

            //普通用户查找草稿表（不包括审核中的商品）
            List<ProductDto> productDtoList1=productDraftService.findProductDraftFromUser(rightId,productName,onlineTime,lineTime,status,manageUserId);
            //管理员查找草稿表
//          List<ProductDto> productDtoList=productDraftService.findProductProductDraftFromAdmin(rightId,productName,onlineTime,lineTime,status,manageUserId);

            productDtoList.addAll(productDtoList1);
            return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productDtoList));
        }
        else{//存在代理人为 查找商品和代理

            //普通用户查找
            List<ProductDto> productDtoList=productService.findProductAndProductAgentFromUser(rightId,productName,onlineTime,lineTime,status,manageUserId,adminId);
            //管理员查找
//          List<ProductDto> productDtoList=productService.findProductAndProductAgentFromAdmin(rightId,productName,onlineTime,lineTime,status,manageUserId,adminId);

            return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productDtoList));
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

    /**
     * 管理人权限移交
     */
    @PostMapping("/updateManager")
    public Result updateManager(@RequestParam("productId") Long productId,@RequestParam("manager") String manager){
        return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productService.updateManager(productId,manager)));
    }
}
