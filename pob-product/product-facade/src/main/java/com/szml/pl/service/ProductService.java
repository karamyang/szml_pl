package com.szml.pl.service;

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

    List<ProductDto> findProductFromUser(String rightId, String productName,
                                         Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);
    List<ProductDto> findProductFromAdmin(String rightId, String productName,
                                          Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);
    List<ProductDto> findProductAndProductAgentFromUser(String rightId, String productName,
                                                        Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId,Long adminId);
    List<ProductDto> findProductAndProductAgentFromAdmin(String rightId, String productName,
                                                         Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId,Long adminId);

    Result stockoffline();
}
