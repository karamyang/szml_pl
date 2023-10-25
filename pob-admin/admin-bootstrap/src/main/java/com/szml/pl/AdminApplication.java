package com.szml.pl;


import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
//@EnableDubbo
@EnableGlobalMethodSecurity(prePostEnabled = true)
@DubboComponentScan(basePackages = {"com.szml.pl.dubbo"})
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
