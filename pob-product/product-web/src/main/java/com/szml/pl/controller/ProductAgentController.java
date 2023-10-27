package com.szml.pl.controller;

import com.alibaba.fastjson.JSON;
import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.security.utils.JwtTokenUtil;
import com.szml.pl.service.ProductAgentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    JwtTokenUtil jwtTokenUtil;

    /**
     * 查询目标商品所有代理人
     */
    @GetMapping("/querylist")
    public Result queryAgent(@RequestParam("productId") Long productId){
        //判断一下是不是商品的管理人
        return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productAgentService.queryListByProductId(productId)));
    }

    /**
     * 增加代理人
     */
    @GetMapping("/addAgent")
    public Result addAgent(@RequestBody ProductDto productDto, @RequestParam("adminId") Long adminId, HttpServletRequest request){
        String header = request.getHeader(tokenHeader);
        Long idFromToken = jwtTokenUtil.getIdFromToken(header);
        if(idFromToken.equals(productDto.getManageUserId())){
            boolean b=productAgentService.save(productDto,adminId);
            return b ? Result.buildSuccessResult() :Result.buildErrorResult();
        }else {
            return Result.buildErrorResult();
        }
    }

    /**
     * 删除代理人
     */
    @GetMapping("/deleteAgent")
    public Result deleteAgent(@RequestBody ProductDto productDto, @RequestParam("adminId") Long adminId, HttpServletRequest request){
        String header = request.getHeader(tokenHeader);
        Long idFromToken = jwtTokenUtil.getIdFromToken(header);
        if(idFromToken.equals(productDto.getManageUserId())){
            boolean b=productAgentService.delete(productDto,adminId);
            return b ? Result.buildSuccessResult() :Result.buildErrorResult();
        }else {
            return Result.buildErrorResult();
        }
    }
}
