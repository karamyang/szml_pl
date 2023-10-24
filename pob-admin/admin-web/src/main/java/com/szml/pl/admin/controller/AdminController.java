package com.szml.pl.admin.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.entity.Admin;
import com.szml.pl.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 用户
 * @author：karma
 * @date: 2023/10/21
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;
    /**
     *  用户登录
     */
    @GetMapping (value = "/login")
    public Result login(@RequestParam String username,@RequestParam String password){
        Map<String,Object> map=adminService.login(username,password);

        if(map.containsKey("success")){//登录成功
            return Result.buildResult(Constants.ResponseCode.SUCCESS);
        }
        else {
            return Result.buildErrorResult();
        }

    }
    /**
     *  用户注册
     */
    @PostMapping(value = "/register")
    public Result register(@RequestBody Admin admin){
        Map<String,Object> map=adminService.register(admin);

        if(map==null|| map.isEmpty()){//登录成功
            return Result.buildResult(Constants.ResponseCode.SUCCESS);
        }
        else {
            return Result.buildErrorResult();
        }
    }

    /**
     *  发送验证码到邮箱
     */
    @GetMapping(value = "/forget")
    public Result sendVerificationCode(@RequestParam String emailAddress){

        adminService.sendVerificationCodeToEmail(emailAddress);

        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    /**
     *  忘记密码
     */
    @GetMapping(value = "/resetPassword")
    public Result resetPassword(@RequestParam String emailAddress,@RequestParam String verificationCode,@RequestParam String newPassword){

        //验证码错误
        if(!adminService.checkVerificationCode(emailAddress,verificationCode)){
            System.out.println("验证码错误");
            return Result.buildErrorResult();
        }

        Map<String,Object> map=adminService.forgetPassword(newPassword,emailAddress);

        System.out.println(map);
        if(map==null|| map.isEmpty()){//修改成功
            return Result.buildResult(Constants.ResponseCode.SUCCESS);
        }
        else {
            return Result.buildErrorResult();
        }

    }
}
