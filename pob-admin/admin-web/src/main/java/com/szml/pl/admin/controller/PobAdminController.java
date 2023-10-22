package com.szml.pl.admin.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 用户
 * @author：karma
 * @date: 2023/10/21
 */
@Controller
@RequestMapping("/admin")
public class PobAdminController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result findpassword(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }
}
