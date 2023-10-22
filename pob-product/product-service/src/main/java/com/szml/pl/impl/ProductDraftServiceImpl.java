package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.ProductDraftDao;
import com.szml.pl.entity.ProductDraft;
import com.szml.pl.service.ProductDraftService;
import com.szml.pl.service.stateflow.StateHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductDraftServiceImpl extends ServiceImpl<ProductDraftDao, ProductDraft> implements ProductDraftService {
    @Resource
    ProductDraftDao productDraftDao;
    @Resource
    StateHandler stateHandler;
}
