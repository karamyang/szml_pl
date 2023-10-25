package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.common.dto.AdminDto;
import com.szml.pl.dto.LoginReq;
import com.szml.pl.entity.Admin;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */

public interface  AdminService extends IService<Admin> {

    String login(LoginReq loginReq);
}
