package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.entity.Product;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
public interface ProductService extends IService<Product> {

    Integer updateManager(Long productId, String manager);
}
