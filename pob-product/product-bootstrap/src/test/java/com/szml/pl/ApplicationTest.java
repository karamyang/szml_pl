package com.szml.pl;

import com.szml.pl.dao.ProductDao;
import com.szml.pl.entity.Product;
import com.szml.pl.service.ProductService;
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
    }


    /**
     * 单一商品查询测试
     */
    @Test
    void selectProductByIdTest(){
        Product product = productService.findProductById(1L);
        System.out.println(product);
    }


    /**
     * 多条件商品查询测试
     */
    @Test
    void selectProduct(){
        List<Product> product = productDao.selectProductAndProductAgentFromAdmin
                (null,null,Timestamp.valueOf("2023-10-22 17:22:22"),null,null,2L,1L);
        for (Product post : product) {
            System.out.println(post);
        }
//        1                      2
//        2023-10-22 12:34:10    2023-10-22 17:22:22
//        2023-10-29 12:34:16    2023-10-22 17:22:22

//        List<Product> product = productDao.selectProductFromUser
//                (null,null, Timestamp.valueOf("2023-10-22 17:22:22"),Timestamp.valueOf("2023-10-22 17:22:22"), 2L,null);
//        for (Product post : product) {
//            System.out.println(post);
//        }
    }


}
