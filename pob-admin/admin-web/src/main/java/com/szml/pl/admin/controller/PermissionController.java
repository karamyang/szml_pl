package com.szml.pl.admin.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 权限
 * @author：karma
 * @date: 2023/10/21
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Result getpermission(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addpermission(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/transferauthority", method = RequestMethod.POST)
    @ResponseBody
    public Result transferauthority(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }
}
