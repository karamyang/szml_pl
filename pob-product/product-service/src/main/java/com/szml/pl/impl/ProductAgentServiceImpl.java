package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.ProductAgentDao;
import com.szml.pl.entity.ProductAgent;
import com.szml.pl.service.ProductAgentService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductAgentServiceImpl extends ServiceImpl<ProductAgentDao, ProductAgent> implements ProductAgentService {
}
