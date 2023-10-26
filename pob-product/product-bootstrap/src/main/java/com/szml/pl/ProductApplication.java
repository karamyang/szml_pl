package com.szml.pl;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: 商品启动类
 * @author：wufengning
 * @date: 2023/10/22
 */
@EnableDubbo
@DubboComponentScan
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = {"com.szml.pl.dao"})
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }
}
