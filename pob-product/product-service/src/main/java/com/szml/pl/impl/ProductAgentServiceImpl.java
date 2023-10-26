package com.szml.pl.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.ProductAgentDao;
import com.szml.pl.entity.ProductAgent;
import com.szml.pl.service.ProductAgentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductAgentServiceImpl extends ServiceImpl<ProductAgentDao, ProductAgent> implements ProductAgentService {
    @Resource
    private ProductAgentDao productAgentDao;

    @Override
    public List<ProductAgent> queryListByProductId(Long prodcutId) {
        LambdaQueryWrapper<ProductAgent> wrapper=new LambdaQueryWrapper();
        wrapper.eq(true,ProductAgent::getProductId,prodcutId);
        List<ProductAgent> productAgents = productAgentDao.selectList(wrapper);
        return productAgents;
    }
}
