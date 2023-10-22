package com.szml.pl.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 商品状态变化流程
 * @author：karma
 * @date: 2023/10/21
 */
@Controller
@RequestMapping("/stateflow")
public class PobProductStateFlowController {

    //发起审核
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result submit(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }
    //编辑保存
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    //审核驳回
    @RequestMapping(value = "/nopass", method = RequestMethod.POST)
    @ResponseBody
    public Result nopass(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }
    //审核通过
    @RequestMapping(value = "/pass", method = RequestMethod.POST)
    @ResponseBody
    public Result pass(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    //上线
    @RequestMapping(value = "/online", method = RequestMethod.POST)
    @ResponseBody
    public Result online(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    //下线
    @RequestMapping(value = "/offline", method = RequestMethod.POST)
    @ResponseBody
    public Result offline(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }
}
