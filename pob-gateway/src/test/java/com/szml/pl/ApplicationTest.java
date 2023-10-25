package com.szml.pl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/24
 */
@SpringBootTest
public class ApplicationTest {
    @Value("${test-a}")
    private String test;

    /**
     * 测试配置中心
     */
    @Test
    void test(){
        System.out.println(test);
    }
}
