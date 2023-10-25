package com.szml.pl.dubbo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.szml.pl.common.Constants;
import com.szml.pl.common.dto.AdminDto;
import com.szml.pl.common.dubbo.AdminDubboService;
import com.szml.pl.common.response.ObjectResult;
import com.szml.pl.dao.AdminDao;
import com.szml.pl.entity.Admin;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/23
 */
//@Service(version = "1.0.0",protocol = "${dubbo.protocol.id}", application = "${dubbo.application.id}",
//        registry = "${dubbo.registry.id}", timeout = 3000)
//@Component
//@DubboService
public class AdminDubboServiceImpl implements AdminDubboService {
    @Resource
    private AdminDao adminDao;
    @Override
    public ObjectResult getIdByName(String username) {
        LambdaQueryWrapper<Admin> wrapper=new LambdaQueryWrapper();
        wrapper.eq(StringUtils.isNotEmpty(username),Admin::getUsername,username);
        Admin admin = adminDao.selectOne(wrapper);
        AdminDto adminDto=new AdminDto();
        BeanUtils.copyProperties(admin,adminDto);
        ObjectResult result=new ObjectResult<>();
        if(admin!=null){
            result.setCode(Constants.ResponseCode.SUCCESS.getCode());
            result.setInfo("RPC调用成功");
            result.setData(adminDto);
            return result;
        }
        result.setCode(Constants.ResponseCode.UN_ERROR.getCode());
        result.setInfo("RPC远程调用失败");
        return result;
    }
}
