package com.szml.pl;

import com.szml.pl.common.Result;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @description: 状态流程测试
 * @author：karma
 * @date: 2023/10/22
 */
@SpringBootTest
public class StateFlowTest {
    @Resource
    ProductService productService;

    @Test
    void submit(){
        Product product = productService.getById(1);
        product.setProductName("test1");
        productService.submit(product);
    }
    @Test
    void nopass(){
        Product product = productService.getById(1);
        Result nopass = productService.nopass(product);
        System.out.println(nopass.getCode()+nopass.getInfo());
    }
    @Test
    void pass(){
        Product product = productService.getById(1);
        Result pass = productService.pass(product);
        System.out.println(pass.getCode()+pass.getInfo());
    }
    @Test
    void online(){
        Product product = productService.getById(1);
        Result online = productService.online(product);
        System.out.println(online.getCode()+online.getInfo());
    }
    @Test
    void offline(){
        Product product = productService.getById(1);
        Result offline = productService.offline(product);
        System.out.println(offline.getCode()+offline.getInfo());
    }

}
