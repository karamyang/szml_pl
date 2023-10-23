package com.szml.pl;

import com.szml.pl.common.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.ProductRecordService;
import com.szml.pl.service.ProductService;
import com.szml.pl.service.stateflow.IStateHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

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
//        productService.submit(product);
    }
    @Test
    void nopass(){
        Product product = productService.getById(1);
//        Result nopass = productService.nopass(product);
//        System.out.println(nopass.getCode()+nopass.getInfo());
    }
    @Test
    void pass(){
        Product product = productService.getById(1);
//        Result pass = productService.pass(product);
//        System.out.println(pass.getCode()+pass.getInfo());
    }
    @Test
    void online(){
        Product product = productService.getById(1);
//        Result online = productService.online(product);
//        System.out.println(online.getCode()+online.getInfo());
    }
    @Test
    void offline(){
        Product product = productService.getById(1);
//        Result offline = productService.offline(product);
//        System.out.println(offline.getCode()+offline.getInfo());
    }

    @Resource
    IStateHandler stateHandler;
    @Test
    void complie(){
        Product product = productService.getById(3);
        product.setProductName("yyx");
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        Result compile = stateHandler.compile(productDto, productDto.getStatus());
        System.out.println(compile.getCode()+compile.getInfo());
//        productService.commitProduct(productDto);
    }

    @Test
    void audit(){
        Product product = productService.getById(3);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        Result audit = stateHandler.audit(productDto, productDto.getStatus());
        System.out.println(audit.getCode()+audit.getInfo());
    }

    @Resource
    ProductRecordService productRecordService;
    @Test
    void record(){

        Integer test = productRecordService.addRecord(productService.getById(3), 1, "test");
    }

}
