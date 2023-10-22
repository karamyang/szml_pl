package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.ProductRecordDao;
import com.szml.pl.entity.ProductRecord;
import com.szml.pl.service.ProductRecordService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductRecordServiceImpl extends ServiceImpl<ProductRecordDao, ProductRecord> implements ProductRecordService {
}
