package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.common.Result;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;

import java.sql.Timestamp;
import java.util.List;

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

    Result batchoperation(List<Product> productList, Integer operation);

    Product findProductById(Long id);

    Page<ProductDto> findProductFromUser(ProductDto productDto, Long current, Long size);
    Page<ProductDto> findProductFromAdmin(ProductDto productDto, Long current, Long size);
    Page<ProductDto> findProductAndProductAgentFromUser(ProductDto productDto, Long current, Long size);

    Page<ProductDto> findProductAndProductAgentFromAdmin(ProductDto productDto, Long current, Long size);
    Result stockoffline();
}
