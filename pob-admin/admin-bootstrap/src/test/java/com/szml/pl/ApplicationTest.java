package com.szml.pl;

import com.szml.pl.dao.AdminDao;
import com.szml.pl.entity.Admin;
import com.szml.pl.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@SpringBootTest
public class ApplicationTest {
    @Resource
    private AdminDao adminDao;

    @Resource
    private AdminService adminService;
    /**
     * sofaBoot应用启动测试
     */
    @Test
    void test(){
        System.out.println("启动成功");
    }

    @Test
    void registerTest(){
        Admin admin=new Admin();
        admin.setUsername("张三");
        admin.setPassword("114514");
        admin.setTelephone("51025156666");
        admin.setEmail("1562032@163.com");
        admin.setCreateTime(new Timestamp(System.currentTimeMillis()));
        admin.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        adminDao.insertAdmin(admin);

        Admin admin1=adminDao.selectAdminByTelephone("51025156666");
        System.out.println(admin1);
    }

    @Test
    void emailTest(){
        adminService.sendVerificationCodeToEmail("13194134680@163.com");
    }
}
