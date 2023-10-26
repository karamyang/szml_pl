package com.szml.pl;

import com.szml.pl.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/25
 */
@SpringBootTest
public class ApiTest {
    @Resource
    private AdminService adminService;

    /**
     * 测试获取权限
     */
    @Test
    void testPermissions(){
        List<String> wfn = adminService.getPermissions("wfn");
        System.out.println(wfn);
    }
}
