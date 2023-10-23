package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.AdminDao;
import com.szml.pl.entity.Admin;
import com.szml.pl.service.AdminService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Resource
    private AdminDao adminDao;


    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String ,Object> map=new HashMap<>();
        if(StringUtils.isBlank(username)){
            map.put("usernameError","账号不能为空!");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("passwordError","密码不能为空!");
            return map;
        }
        Admin admin=adminDao.selectAdminByUsername(username);
        if(admin==null){
            map.put("adminError","账号不存在!");
            return map;
        }
        map.put("success","登录成功");
        return map;
    }

    @Override
    public Map<String, Object> register(Admin admin) {
        Map<String,Object> map=new HashMap<>();

        if(admin==null){
            throw new IllegalArgumentException("参数不能为空!");
        }
        if(StringUtils.isBlank(admin.getUsername())){
            map.put("usernameError","账号不能为空!");
            return map;
        }
        if(StringUtils.isBlank(admin.getPassword())){
            map.put("passwordError","密码不能为空!");
            return map;
        }
        if(StringUtils.isBlank(admin.getEmail())){
            map.put("emailError","邮箱不能为空!");
            return map;
        }
        //验证账号是否已存在
        Admin admin1=adminDao.selectAdminByUsername(admin.getUsername());
        if(admin1!=null){
            map.put("usernameMsg","该账号已存在!");
            return map;
        }
        //验证邮箱是否已存在
        admin1=adminDao.selectAdminByEmail(admin.getEmail());
        if(admin1!=null){
            map.put("emailMsg","该邮箱已被注册!");
            return map;
        }

        //验证手机是否已存在
        admin1=adminDao.selectAdminByTelephone(admin.getTelephone());
        if(admin1!=null){
            map.put("emailMsg","该手机已被注册!");
            return map;
        }
        admin.setCreateTime(new Timestamp(System.currentTimeMillis()));

        //注册用户
        adminDao.insertAdmin(admin);

        return map;
    }
}
