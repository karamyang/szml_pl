package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.jdbc.TimeUtil;
import com.szml.pl.common.Constants;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.dao.ProductDraftDao;
import com.szml.pl.dao.ProductRecordDao;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.entity.ProductRecord;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.ProductRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductDraftServiceImpl extends ServiceImpl<ProductDraftDao, ProductDraft> implements ProductDraftService {
    @Resource
    private ProductDao productDao;
    @Resource
    private ProductDraftDao draftDao;
    @Resource
    private ProductRecordService productRecordService;
    @Override
    @Transactional
    public Boolean saveDraft(ProductDto productDto) {
        //1.转化数据
        ProductDraft productDraft=new ProductDraft();
        BeanUtils.copyProperties(productDto,productDraft);
        productDraft.setStatus(Constants.ProductState.DRAFT.getCode());
        Long productId=productDto.getId();
        //2.判断草稿表中是否存在
        ProductDraft productDraft1 = draftDao.selectById(productId);
        if(productDraft1!=null){
            //3.更新商品草稿数据
            productDraft.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            int flag = draftDao.updateById(productDraft);
            return flag>0?true:false;
        }
        //5.否则新增商品草稿数据
        productDraft.setCreateTime(new Timestamp(System.currentTimeMillis()));
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        productRecordService.addRecord(product,Constants.ProductRecordState.SAVEDRAFT.getCode(),Constants.ProductRecordState.SAVEDRAFT.getInfo());
        draftDao.insert(productDraft);
        return true;
    }



    @Override
    public Page<ProductDto> findProductDraftFromUser(ProductDto productDto, Long current, Long size){

        Page<ProductDraft> productDrafts=draftDao.selectProductDraftFromUser(new Page<>(current,size),productDto.getRightId(),productDto.getProductName(),productDto.getOnlineTime(),productDto.getLineTime(),productDto.getStatus(),productDto.getManageUserId());
        Page<ProductDto> productDtos=new Page<>();
        for(ProductDraft product:productDrafts.getRecords()) {
            ProductDto dto=new ProductDto();
            BeanUtils.copyProperties(product,dto);
            productDtos.getRecords().add(dto);
        }
        return productDtos;
    }

    @Override
    public Page<ProductDto> findProductDraftFromAdmin(ProductDto productDto, Long current, Long size){
        Page<ProductDraft> productDrafts=draftDao.selectProductDraftFromAdmin(new Page<>(current,size),productDto.getRightId(),productDto.getProductName(),productDto.getOnlineTime(),productDto.getLineTime(),productDto.getStatus(),productDto.getManageUserId());
        Page<ProductDto> productDtos=new Page<>();
        for(ProductDraft product:productDrafts.getRecords()) {
            ProductDto dto=new ProductDto();
            BeanUtils.copyProperties(product,dto);
            productDtos.getRecords().add(dto);
        }
        return productDtos;
    }

}
