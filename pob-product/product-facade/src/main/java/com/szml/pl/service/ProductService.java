package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.common.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
public interface ProductService extends IService<Product> {
    Result audit(ProductDto product);

    Result nopass(ProductDto product);

    Result pass(ProductDto product);

    Result online(ProductDto product);

    Result offline(ProductDto product);

    Result submit(ProductDto product);

    Boolean commitProduct(ProductDto product);
}
