package com.szml.pl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.security.utils.JwtTokenUtil;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    /**
     * 商品查询（条件查询）
     */
    @RequestMapping(value = "/getsProduct",method = RequestMethod.POST)
    public Result queryProduct(HttpServletRequest request, @RequestBody ProductDto productDto, @RequestParam Long current, @RequestParam Long size){
        //current为当前页数  size为每页数量

        if(productDto.getAdminId()==null){//代理人为空 查询商品和草稿

            String authHeader = request.getHeader(this.tokenHeader);
            if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
                String authToken = authHeader.substring(this.tokenHead.length());
                //从token中获取用户id
                String role = jwtTokenUtil.getRoleFromToken(authToken);
                return role.equals("operator")? Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productService.findProductFromUser(productDto,current,size))):Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productService.findProductFromAdmin(productDto,current,size)));
            }
            else return Result.buildErrorResult();
        }
        else{//存在代理人为 查找商品和代理

            String authHeader = request.getHeader(this.tokenHeader);
            if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
                String authToken = authHeader.substring(this.tokenHead.length());
                //从token中获取用户id
                String role = jwtTokenUtil.getRoleFromToken(authToken);
                return role.equals("operator")? Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productService.findProductAndProductAgentFromUser(productDto,current,size))):Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productService.findProductAndProductAgentFromAdmin(productDto,current,size)));
            }
            else return Result.buildErrorResult();
        }
    }
    /**
     * 草稿查询（条件查询）
     */
    @RequestMapping(value = "/getsProductDraft",method = RequestMethod.POST)
    public Result queryProductDraft(HttpServletRequest request,@RequestBody ProductDto productDto,@RequestParam Long current,@RequestParam Long size){
        //current为当前页数  size为每页数量

        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());
            //从token中获取用户role
            String role = jwtTokenUtil.getRoleFromToken(authToken);
            return role.equals("operator")? Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productDraftService.findProductDraftFromUser(productDto,current,size))):Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productDraftService.findProductDraftFromAdmin(productDto,current,size)));
        }
        else return Result.buildErrorResult();

    }
    /**
     * 单一商品查询(商品详情)
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Result queryProduct(HttpServletRequest request,@RequestBody ProductDto productDto){

        if(!productDto.getStatus().equals(1)){//当前商品不是草稿
            Product product=productService.findProductById(productDto.getId());
            if(product == null){
                return Result.buildErrorResult();
            }
            return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productDto));
        }
        ProductDraft productDraft=productDraftService.findProductDraftById(productDto.getId());
        if(productDraft == null){
            return Result.buildErrorResult();
        }

        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());
            //从token中获取用户id
            String role = jwtTokenUtil.getRoleFromToken(authToken);
            Long id = jwtTokenUtil.getIdFromToken(authToken);

            return productDraft.getCreateUserId().equals(id)?  Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productDto)):Result.buildErrorResult();
        }
        else return Result.buildErrorResult();
    }

    /**
     * 管理人权限移交
     */
    @PostMapping("/updateManager")
    public Result updateManager(@RequestParam("productId") Long productId,@RequestParam("manager") String manager){
        //判断是不是商品的管理人
        return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productService.updateManager(productId,manager)));
    }
}
