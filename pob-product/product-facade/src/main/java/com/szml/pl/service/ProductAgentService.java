package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.ProductAgent;

import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
public interface ProductAgentService extends IService<ProductAgent> {
    /**
     * 根据商品id查询代理人
     * @param prodcutId
     * @return
     */
    List<ProductAgent> queryListByProductId(Long prodcutId);
    /**
     * 查询用户是否是商品代理人
     * @param prodcutId
     * @return
     */
    boolean selectprodcutagent(Long prodcutId,Long adminId);

    boolean save(ProductDto productDto, Long adminId);

    boolean delete (ProductDto productDto, Long adminId);
}
