package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.ProductRecordDao;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductRecord;
import com.szml.pl.service.ProductRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductRecordServiceImpl extends ServiceImpl<ProductRecordDao, ProductRecord> implements ProductRecordService {
    @Resource
    private ProductRecordDao productRecordDao;
    @Override
    public Integer addRecord(Product product,Integer state,String desc) {
        ProductRecord record=new ProductRecord();
        record.setProductId(product.getId());
        //其实此处应该获取当前登录用户的id
        record.setAdminId(product.getManageUserId());
        record.setRecord(state);
        record.setCreateTime(new Timestamp(System.currentTimeMillis()));
        record.setRecordDescription(desc);
        return productRecordDao.insert(record);
    }
}
