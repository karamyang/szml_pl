package com.szml.pl.controller;

import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Http;
import com.szml.pl.common.response.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductAgent;
import com.szml.pl.security.utils.JwtTokenUtil;
import com.szml.pl.service.ProductAgentService;
import com.szml.pl.service.ProductService;
import com.szml.pl.service.stateflow.IStateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @description: 商品状态变化流程
 * @author：karma
 * @date: 2023/10/21
 */
@RestController
@RequestMapping("/product/stateflow")
public class ProductStateFlowController {

    @Resource
    ProductService productService;
    @Resource
    IStateHandler stateHandler;
    @Resource
    ProductAgentService productAgentService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    JwtTokenUtil jwtTokenUtil;
    /**
     * 发起审核
     */
    @PostMapping(value = "/audit")
    public Result audit(@RequestBody ProductDto productDto, HttpServletRequest request) {
        //判断一下是不是商品的管理人
        String header = request.getHeader(tokenHeader);
        Long idFromToken = jwtTokenUtil.getIdFromToken(header);
        boolean selectprodcutagent = productAgentService.selectprodcutagent(productDto.getId(), idFromToken);
        if(selectprodcutagent){
                return stateHandler.audit(productDto,productDto.getStatus());
        }
        return Result.buildErrorResult();
    }
    /**
     * 提交
     */
    @PostMapping(value = "/submit")
    public Result submit(@RequestBody ProductDto productDto, HttpServletRequest request) {
        //判断一下是不是商品的管理人
        String header = request.getHeader(tokenHeader);
        Long idFromToken = jwtTokenUtil.getIdFromToken(header);
        boolean selectprodcutagent = productAgentService.selectprodcutagent(productDto.getId(), idFromToken);
        if(selectprodcutagent){
            return stateHandler.submit(productDto,productDto.getStatus());
        }
        return Result.buildErrorResult();
    }

    /**
     * 审核驳回
     */
    @PostMapping(value = "/nopass")
    @PreAuthorize("hasAnyAuthority('stateflow:nopass','all')")
    public Result nopass(@RequestBody ProductDto productDto) {

        return stateHandler.nopass(productDto,productDto.getStatus());
    }

    /**
     * 审核通过
     */
    @PostMapping(value = "/pass")
    @PreAuthorize("hasAnyAuthority('stateflow:pass','all')")
    public Result pass(@RequestBody ProductDto productDto) {
        return stateHandler.pass(productDto,productDto.getStatus());
    }

    /**
     * 上线
     */
    @PostMapping(value = "/online")
    @PreAuthorize("hasAnyAuthority('stateflow:online','all')")
    public Result online(@RequestBody ProductDto productDto) {
        System.out.println(productDto.toString());
        return stateHandler.online(productDto,productDto.getStatus());
    }

    /**
     * 下线
     */
    @PostMapping(value = "/offline")
    @PreAuthorize("hasAnyAuthority('stateflow:offline','all')")
    public Result offline(@RequestBody ProductDto productDto) {
        return stateHandler.offline(productDto,productDto.getStatus());
    }
    /**
     * 编辑
     */
    @PostMapping(value = "/compile")
    public Result compile(@RequestBody ProductDto productDto, HttpServletRequest request) {
        //判断一下是不是商品的管理人
        String header = request.getHeader(tokenHeader);
        Long idFromToken = jwtTokenUtil.getIdFromToken(header);
        boolean selectprodcutagent = productAgentService.selectprodcutagent(productDto.getId(), idFromToken);
        if(selectprodcutagent){
            return stateHandler.compile(productDto,productDto.getStatus());
        }
        return Result.buildErrorResult();
    }

    /**
     * 批量操作
     */
    @PostMapping(value = "/batchoperation")
    @PreAuthorize("hasAnyAuthority('stateflow:batchoperation','all')")
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
