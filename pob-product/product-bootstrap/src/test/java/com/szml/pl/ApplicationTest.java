package com.szml.pl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szml.pl.dao.ProductCategoryDao;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.dao.ProductDraftDao;
import com.szml.pl.dao.ProductRecordDao;
import com.szml.pl.dto.CategoryDto;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductCategory;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.entity.ProductRecord;
import com.szml.pl.service.ProductCategoryService;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.ProductRecordService;
import com.szml.pl.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @description: 集成测试
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
    private ProductCategoryDao categoryDao;
    @Resource
    private ProductCategoryService categoryService;
    @Resource
    private ProductDraftService productDraftService;
    @Resource
    private ProductRecordService productRecordService;
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
//        productService.submit(byId);
    }

    /**
     * 保存&更新草稿测试
     * 第一次，没有设置id，可以自动生成id,可以进行插入
     * 但是第二次没有设置id就会出现name的冲突
     * 第三次仍然又问题，通过调试发现是由于更新草稿后没有结束方法
     * 第四次调试更新商品通过
     * 第五次调试将商品移到草稿表中，并记录操作记录，通过
     */
    @Test
    void saveDaftTest(){
        ProductDto productDto=new ProductDto();
        productDto.setId(1716017178198679553L);
        productDto.setProductName("测试");
        productDto.setDescription("用于测试的用例");
        productDto.setType("goods");
        productDto.setCategoryId(2L);
        productDto.setRightId("123123412");
        productDto.setExchangeType("[1,2]");
        productDto.setCash(16.3);
        productDto.setIntegral(32L);
        productDto.setStock(100L);
        productDto.setRestriction(2L);
        productDto.setImgUrl("");
        productDto.setDescription("hnjkhbaskjdfhs");
        productDto.setDetail("asdjoashdjklahd");
        productDto.setOnlineTime(new Timestamp(System.currentTimeMillis()));
        productDto.setLineTime(new Timestamp(System.currentTimeMillis()));
        productDto.setStatus(3);
        productDto.setCreateUserId(1L);
        productDto.setManageUserId(1L);
        Boolean b = productDraftService.saveDraft(productDto);
        System.out.println(b);
    }

    /**
     * 测试提交商品方法
     * 第一次删除草稿中的商品并添加到商品中，测试通过
     * 第二次修改商品信息
     */
    @Test
    void commitTest(){
        ProductDto productDto=new ProductDto();
        productDto.setId(1716017178198679553L);
        productDto.setProductName("测试");
        productDto.setDescription("用于测试的用例");
        productDto.setType("goods");
        productDto.setCategoryId(2L);
        productDto.setRightId("123123412");
        productDto.setExchangeType("[1,2]");
        productDto.setCash(16.3);
        productDto.setIntegral(39L);
        productDto.setStock(100L);
        productDto.setRestriction(2L);
        productDto.setImgUrl("");
        productDto.setDescription("hnjkhbaskjdfhs");
        productDto.setDetail("asdjoashdjklahd");
        productDto.setOnlineTime(new Timestamp(System.currentTimeMillis()));
        productDto.setLineTime(new Timestamp(System.currentTimeMillis()));
        productDto.setStatus(3);
        productDto.setCreateUserId(1L);
        productDto.setManageUserId(1L);
        Boolean b = productService.commitProduct(productDto);
        System.out.println(b);
    }
    /**
     * 测试类别查询的排序
     * warpper条件没有生效
     * 手写SQL生效
     */
    @Test
    void queryCategoryTest(){
//        LambdaQueryWrapper<ProductCategory> wrapper=new LambdaQueryWrapper();
//        wrapper.orderBy(false,true,ProductCategory::getCategoryLevel);
//        List<ProductCategory> productCategories = categoryDao.selectList(wrapper);
//        List<ProductCategory> productCategories = categoryDao.queryCategoryList();
//        for (ProductCategory productCategory : productCategories) {
//            System.out.println(productCategory.toString());
//        }
        List<CategoryDto> categoryDtoList = categoryService.queryCategoryList();
        for (CategoryDto categoryDto : categoryDtoList) {
            System.out.println(categoryDto.toString());
        }
    }

    /**
     * 测试查询操作记录
     */
    @Test
    void recordQuery(){
        List<ProductRecord> list = productRecordService.list();
        System.out.println(list);
    }
    @Resource
    private ProductRecordDao productRecordDao;
    @Resource
    private ProductDraftDao productDraftDao;
    /**
     * 分页查询测试
     */
    @Test
    void queryTest(){
        //操作表
//        System.out.println(JSON.toJSONString("操作表："+productRecordService.page(new Page<>(2,5))));
        Page<ProductDraft> lists=productDraftDao.selectProductDraftFromAdmin(new Page<>(2,2),null,null,null,null,null,null);
        System.out.println("草稿表多条件查询："+JSON.toJSONString(lists));
//        Page<Product> list=productDao.selectProductAndProductAgentFromAdmin(new Page<>(2,2),null,null,null,null,null,null,2L);
//        System.out.println("商品表和权限表多条件查询："+JSON.toJSONString(list));
    }
}

