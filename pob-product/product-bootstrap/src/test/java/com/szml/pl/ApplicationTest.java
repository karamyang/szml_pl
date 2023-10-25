package com.szml.pl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.szml.pl.common.dto.AdminDto;
import com.szml.pl.common.dubbo.AdminDubboService;
import com.szml.pl.common.response.ObjectResult;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductAgent;
import com.szml.pl.service.ProductAgentService;
import com.szml.pl.service.ProductService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

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
    @Resource
    private ProductAgentService productAgentService;
    @Resource
    private UserDetailsService userDetailsService;
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
//    @SofaReference(interfaceType = AdminDubboService.class, jvmFirst = false,
//            binding = @SofaReferenceBinding(bindingType = "bolt"))
    @DubboReference
    private AdminDubboService adminDubboService;
    /**
     * 远程调用集成测试
     * 直连测试通过
     */
    @Test
    void RPCTest(){
        ObjectResult<AdminDto> pobAdmin = adminDubboService.getUserByName("wfn");
        System.out.println(pobAdmin.getData());

    }
    /**
     * 集成测试移交管理员
     * 测试通过
     */
    @Test
    void exchangeManager(){
        //Boolean aBoolean = productService.updateManager(2L, "wfn");
        //System.out.println(aBoolean);
    }

    /**
     * 根据商品Id查询所有的代理人集成测试
     */
    @Test
    void AgentTest(){
        List<ProductAgent> productAgents = productAgentService.queryListByProductId(4L);
        for (ProductAgent productAgent : productAgents) {
            System.out.println(productAgent.toString());
        }
    }
}
