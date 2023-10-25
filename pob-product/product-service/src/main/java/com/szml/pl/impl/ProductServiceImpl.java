package com.szml.pl.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.common.dubbo.AdminDubboService;
import com.szml.pl.common.response.ObjectResult;
import com.szml.pl.dao.ProductDao;
import com.szml.pl.common.dto.AdminDto;
import com.szml.pl.entity.Product;
import com.szml.pl.service.ProductService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description: 商品服务
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Resource
    private ProductDao productDao;

    private AdminDubboService adminDubboService;
    @Override
    @Transactional
    public Integer updateManager(Long productId, String manager) {
        Product product = productDao.selectById(productId);
        Long createUserId = product.getCreateUserId();
        //判断当前操作用户是否可以移交管理员
        ObjectResult result = adminDubboService.getIdByName(manager);
        AdminDto adminDto= (AdminDto) result.getData();
        product.setManageUserId(adminDto.getId());
        int flag = productDao.updateById(product);
        return flag;
    }
}
