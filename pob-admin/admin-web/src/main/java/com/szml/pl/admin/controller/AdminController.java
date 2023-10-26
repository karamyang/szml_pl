package com.szml.pl.admin.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.dto.LoginReq;
import com.szml.pl.entity.Admin;
import com.szml.pl.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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


    @PostMapping("/login")
    public Result login(@RequestBody LoginReq loginReq){
        String token = adminService.login(loginReq);
        if(token==null){
            return Result.buildResult(Constants.ResponseCode.UN_ERROR,"登录失败");
        }
        return Result.buildResult(Constants.ResponseCode.SUCCESS,token);
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
