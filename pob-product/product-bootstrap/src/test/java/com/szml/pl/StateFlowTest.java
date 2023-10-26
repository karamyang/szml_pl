package com.szml.pl;

import com.szml.pl.common.Constants;
import com.szml.pl.common.response.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.service.ProductRecordService;
import com.szml.pl.service.ProductService;
import com.szml.pl.service.mq.rocketmq.producer.LineProducer;
import com.szml.pl.service.stateflow.IStateHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    LineProducer lineProducer;
    @Test
    void mq(){
        Product product = productService.getById(110);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        System.out.println(productDto.toString());
        lineProducer.sendPobOnline(productDto);
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    void piliangcesi(){
        List<Product> productList=new ArrayList<>();
        productList.add(productService.getById(1));
        productList.add(productService.getById(2));
        productList.add(productService.getById(3));
        productList.add(productService.getById(4));
        productList.add(productService.getById(110));
        Result batchoperation = productService.batchoperation(productList, Constants.ProductRecordState.ONLINE.getCode());
        System.out.println(batchoperation.getCode()+batchoperation.getInfo());
    }


}
