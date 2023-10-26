package com.szml.pl;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @description: Admin启动类
 * @author：wufengning
 * @date: 2023/10/22
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@DubboComponentScan(basePackages = {"com.szml.pl.dubbo"})
@EnableDubbo
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
