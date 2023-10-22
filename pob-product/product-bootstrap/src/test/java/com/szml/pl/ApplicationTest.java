package com.szml.pl;

import com.szml.pl.dao.ProductDao;
import com.szml.pl.entity.Product;
import com.szml.pl.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@SpringBootTest
public class ApplicationTest {
    @Resource
    private ProductDao productDao;
    @Resource
    private ProductService productService;
    /**
     * sofaBoot应用启动测试
     */
    @Test
    void test(){
        System.out.println("启动成功");
    }

    /**
     * 数据库连接测试
     */
    @Test
    void connectionTest(){
//        List<Product> products = productDao.selectList(null);
//        for (Product product : products) {
//            System.out.println(product.toString());
//        }
        Product byId = productService.getById(1);
        System.out.println(byId.toString());
        productService.submit(byId);
    }
}
