package com.szml.pl.controller;

import com.alibaba.fastjson.JSON;
import com.szml.pl.dto.LoginReq;
import com.szml.pl.service.AdminService;
import net.sf.jsqlparser.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/24
 */
@RestController
@RequestMapping("/admin")
public class TestController {
    @Resource
    private AdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('test')")
    public String test(){
        return "test";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginReq loginReq){
        String token = adminService.login(loginReq);
        if (token == null) {
            return "登录失败";
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return JSON.toJSONString(tokenMap);
    }
}
