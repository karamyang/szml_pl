package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.common.Result;
import com.szml.pl.entity.Product;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
public interface ProductService extends IService<Product> {
    Result audit(Product product);

    Result nopass(Product product);

    Result pass(Product product);

    Result online(Product product);

    Result offline(Product product);

    Result submit(Product product);

    Result compile(Product product);
}
