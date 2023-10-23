package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.entity.Admin;

import java.util.Map;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
public interface  AdminService extends IService<Admin> {

    Map<String,Object> login(String username,String password);
    Map<String,Object> register(Admin admin);
}
