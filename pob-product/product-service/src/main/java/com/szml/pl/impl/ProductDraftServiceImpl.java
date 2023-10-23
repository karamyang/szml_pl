package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.ProductDraftDao;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.ProductDraftService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductDraftServiceImpl extends ServiceImpl<ProductDraftDao, ProductDraft> implements ProductDraftService {

    @Resource
    private ProductDraftDao productDraftDao;
    public List<ProductDto> findProductDraftFromUser(String rightId, String productName,
                                              Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<ProductDraft> productDrafts=productDraftDao.selectProductDraftFromUser(rightId,productName,onlineTime,lineTime,status,manageUserId);
        for(ProductDraft productDraft:productDrafts) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(productDraft,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }
    public List<ProductDto> findProductDraftFromAdmin(String rightId, String productName,
                                               Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId){
        List<ProductDto> productDtos=new ArrayList<>();
        List<ProductDraft> productDrafts=productDraftDao.selectProductDraftFromAdmin(rightId,productName,onlineTime,lineTime,status,manageUserId);
        for(ProductDraft productDraft:productDrafts) {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(productDraft,productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }

}
