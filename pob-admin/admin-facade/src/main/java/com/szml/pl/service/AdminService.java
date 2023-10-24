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
    Map<String,Object> login(String username, String password);
    Map<String,Object> register(Admin admin);

    //发送验证码到邮箱
    void sendVerificationCodeToEmail(String emailAddress);

    //判断验证码
    boolean checkVerificationCode(String emailAddress,String VerificationCode);

    //忘记密码
    Map<String,Object> forgetPassword(String password,String email);
}
