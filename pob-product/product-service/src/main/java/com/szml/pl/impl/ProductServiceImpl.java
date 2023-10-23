package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductCategory;
import com.szml.pl.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 商品服务
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Resource
    private ProductDao productDao;

    public Product findProductById(Long id){
        return productDao.selectProductById(id);
    }

    public List<ProductDto> findProductFromUser(String rightId, String productName,
                                      Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productDao.selectProductFromUser(rightId,productName,onlineTime,lineTime,status,manageUserId);
        for(Product product:products) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public List<ProductDto> findProductFromAdmin(String rightId, String productName,
                                          Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productDao.selectProductFromAdmin(rightId,productName,onlineTime,lineTime,status,manageUserId);
        for(Product product:products) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }


    public List<ProductDto> findProductAndProductAgentFromUser(String rightId, String productName,
                                                  Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId,Long adminId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productDao.selectProductAndProductAgentFromUser(rightId,productName,onlineTime,lineTime,status,manageUserId,adminId);
        for(Product product:products) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public List<ProductDto> findProductAndProductAgentFromAdmin(String rightId, String productName,
                                                                Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId,Long adminId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productDao.selectProductAndProductAgentFromAdmin(rightId,productName,onlineTime,lineTime,status,manageUserId,adminId);
        for(Product product:products) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }

}
