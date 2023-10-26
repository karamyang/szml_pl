package com.szml.pl.admin.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 权限
 * @author：karma
 * @date: 2023/10/21
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @PostMapping(value = "/get")
    @ResponseBody
    public Result getpermission(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result addpermission(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    @PostMapping(value = "/transferauthority")
    @ResponseBody
    public Result transferauthority(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }
}
