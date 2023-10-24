package com.szml.pl.admin.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.entity.Admin;
import com.szml.pl.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 用户
 * @author：karma
 * @date: 2023/10/21
 */
@Controller
@RequestMapping("/admin")
public class PobAdminController {

    @Resource
    private AdminService adminService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String username,String password){
        Map<String,Object> map=adminService.login(username,password);

        if(map.containsKey("success")){//登录成功
            return Result.buildResult(Constants.ResponseCode.SUCCESS);
        }
        else {
            return Result.buildErrorResult();
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(Admin admin){
        Map<String,Object> map=adminService.register(admin);

        if(map==null|| map.isEmpty()){//登录成功
            return Result.buildResult(Constants.ResponseCode.SUCCESS);
        }
        else {
            return Result.buildErrorResult();
        }
    }

    //发送验证码到邮箱
    @RequestMapping(value = "/forget", method = RequestMethod.GET)
    @ResponseBody
    public Result sendVerificationCode(@RequestParam String emailAddress){

        adminService.sendVerificationCodeToEmail(emailAddress);

        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    //忘记密码
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Result resetPassword(@RequestParam String emailAddress,@RequestParam String verificationCode,@RequestParam String newPassword){

        //验证码错误
        if(!adminService.checkVerificationCode(emailAddress,verificationCode)){
            return Result.buildErrorResult();
        }

        Map<String,Object> map=adminService.forgetPassword(emailAddress,newPassword);

        if(map==null|| map.isEmpty()){//修改成功
            return Result.buildResult(Constants.ResponseCode.SUCCESS);
        }
        else {
            return Result.buildErrorResult();
        }

    }
}
