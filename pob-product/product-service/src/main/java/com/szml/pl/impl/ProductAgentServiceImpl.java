package com.szml.pl.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.ProductAgentDao;
import com.szml.pl.dto.ProductDto;
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
    @Resource
    private ProductAgentService productAgentService;

    @Override
    public List<ProductAgent> queryListByProductId(Long prodcutId) {
        LambdaQueryWrapper<ProductAgent> wrapper=new LambdaQueryWrapper();
        wrapper.eq(true,ProductAgent::getProductId,prodcutId);
        List<ProductAgent> productAgents = productAgentDao.selectList(wrapper);
        return productAgents;
    }

    @Override
    public boolean selectprodcutagent(Long prodcutId,Long adminId) {
        QueryWrapper<ProductAgent> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_id",adminId);
        wrapper.eq("prodcut_id",prodcutId);
        ProductAgent productAgent = productAgentDao.selectOne(wrapper);
        if(productAgent==null){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public boolean save(ProductDto productDto, Long adminId) {
        ProductAgent productAgent = new ProductAgent();
        productAgent.setProductId(productDto.getId());
        productAgent.setAdminId(adminId);
        int insert = productAgentDao.insert(productAgent);
        return insert>0 ? true:false;
    }

    @Override
    public boolean delete(ProductDto productDto, Long adminId) {
        QueryWrapper<ProductAgent> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_id",adminId);
        wrapper.eq("prodcut_id",productDto.getId());
        int delete = productAgentDao.delete(wrapper);
        return delete>0 ? true:false;
    }
}
