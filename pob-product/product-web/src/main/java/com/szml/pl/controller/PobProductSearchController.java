package com.szml.pl.controller;

import com.szml.pl.common.Constants;
import com.szml.pl.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author：karma
 * @date: 2023/10/21
 */
@Controller
@RequestMapping("/search")
public class PobProductSearchController {
    //查询所有商品
    @RequestMapping(value = "/roductall", method = RequestMethod.POST)
    @ResponseBody
    public Result productall(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    //条件查询商品
    @RequestMapping(value = "/conditionproductall", method = RequestMethod.POST)
    @ResponseBody
    public Result conditionproductall(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }

    //商品操作记录
    @RequestMapping(value = "/record", method = RequestMethod.POST)
    @ResponseBody
    public Result record(){
        return Result.buildResult(Constants.ResponseCode.SUCCESS);
    }
}
