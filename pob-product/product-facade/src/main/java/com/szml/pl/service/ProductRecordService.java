package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductRecord;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
public interface ProductRecordService extends IService<ProductRecord> {
    /**
     * 添加用户操作记录
     */
    Integer addRecord(Product product,Integer state,String desc);
}
