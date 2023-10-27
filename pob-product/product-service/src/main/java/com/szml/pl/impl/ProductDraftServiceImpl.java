package com.szml.pl.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.jdbc.TimeUtil;
import com.szml.pl.common.Constants;
import com.szml.pl.common.ids.IIdGenerator;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.dao.ProductDraftDao;
import com.szml.pl.dao.ProductRecordDao;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.entity.ProductRecord;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.ProductRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(ProductDraftServiceImpl.class);

    @Resource
    private ProductDao productDao;
    @Resource
    private ProductDraftDao draftDao;
    @Resource
    private ProductRecordService productRecordService;
    @Resource
    IIdGenerator idGenerator;
    @Override
    @Transactional
    public Boolean saveDraft(ProductDto productDto) {
        //1.转化数据
        ProductDraft productDraft=new ProductDraft();
        BeanUtils.copyProperties(productDto,productDraft);
        productDraft.setStatus(Constants.ProductState.DRAFT.getCode());
        Long productId=productDto.getId();
        String productName = productDraft.getProductName();
        //2.判断草稿表中是否存在
        if(productId!=null){
            //3.更新商品草稿数据
            productDraft.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            int flag = draftDao.updateById(productDraft);
            Product product=new Product();
            BeanUtils.copyProperties(productDraft,product);
            productRecordService.addRecord(product,Constants.ProductRecordState.SAVEDRAFT.getCode(),Constants.ProductRecordState.SAVEDRAFT.getInfo());
            return flag>0?true:false;
        }
        productDraft.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //4.否则新增商品草稿数据
        draftDao.insert(productDraft);
        LambdaQueryWrapper<ProductDraft> wrapper=new LambdaQueryWrapper();
        wrapper.eq(ProductDraft::getProductName,productName);
        ProductDraft productDraft1 = draftDao.selectOne(wrapper);
        //3、新增操作记录
        Product product=new Product();
        BeanUtils.copyProperties(productDraft1,product);
        productRecordService.addRecord(product,Constants.ProductRecordState.SAVEDRAFT.getCode(),Constants.ProductRecordState.SAVEDRAFT.getInfo());
        return true;
    }




    @Override
    public Page<ProductDto> findProductDraftFromUser(ProductDto productDto, Long current, Long size){

        Page<ProductDraft> productDrafts=draftDao.selectProductDraftFromUser(new Page<>(current,size),productDto.getRightId(),productDto.getProductName(),productDto.getOnlineTime(),productDto.getLineTime(),productDto.getStatus(),productDto.getManageUserId());
        Page<ProductDto> productDtos=new Page<>();
        productDtos.setCurrent(productDrafts.getCurrent());
        productDtos.setSize(productDrafts.getSize());
        productDtos.setTotal(productDrafts.getTotal());
        productDtos.setPages(productDrafts.getPages());
        List<ProductDto> list=new ArrayList<>();
        for(ProductDraft product:productDrafts.getRecords()) {
            ProductDto dto=new ProductDto();
            BeanUtils.copyProperties(product,dto);
            list.add(dto);
        }
        productDtos.setRecords(list);
        return productDtos;
    }

    @Override
    public Page<ProductDto> findProductDraftFromAdmin(ProductDto productDto, Long current, Long size){

        Page<ProductDraft> productDrafts=draftDao.selectProductDraftFromAdmin(new Page<>(current,size),productDto.getRightId(),productDto.getProductName(),productDto.getOnlineTime(),productDto.getLineTime(),productDto.getStatus(),productDto.getManageUserId());
        Page<ProductDto> productDtos=new Page<>();
        productDtos.setCurrent(productDrafts.getCurrent());
        productDtos.setSize(productDrafts.getSize());
        productDtos.setTotal(productDrafts.getTotal());
        productDtos.setPages(productDrafts.getPages());
        List<ProductDto> list=new ArrayList<>();
        for(ProductDraft product:productDrafts.getRecords()) {
            ProductDto dto=new ProductDto();
            BeanUtils.copyProperties(product,dto);
            list.add(dto);
        }
        productDtos.setRecords(list);
        return productDtos;
    }
    @Override
    public ProductDraft findProductDraftById(Long id){
        return draftDao.selectProductDraftById(id);
    }
}
