package com.szml.pl.admin.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import com.szml.pl.entity.Admin;
import com.szml.pl.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

        if(map.containsKey("success")){
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

        if(map==null|| map.isEmpty()){
            return Result.buildResult(Constants.ResponseCode.SUCCESS);
        }
        else {
            return Result.buildErrorResult();
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result findpassword(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }
}
