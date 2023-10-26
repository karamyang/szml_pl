package com.szml.pl.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.service.ProductRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@RestController
@RequestMapping("/record")
public class ProductRecordController {

    @Resource
    private ProductRecordService productRecordService;

    /**
     * 操作记录列表查询？分页查询
     */
    @GetMapping("/getRecord")
    public Result queryRecordList(@RequestParam Long current, @RequestParam Long size){
        return Result.buildResult(Constants.ResponseCode.SUCCESS, JSON.toJSONString(productRecordService.page(new Page<>(current,size))));
    }

}
