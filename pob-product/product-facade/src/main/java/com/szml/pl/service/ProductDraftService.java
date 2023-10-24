package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.ProductDraft;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
public interface ProductDraftService extends IService<ProductDraft> {

    Boolean saveDraft(ProductDto productDto);

    List<ProductDto> findProductDraftFromUser(String rightId, String productName,
                                              Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);
    List<ProductDto> findProductDraftFromAdmin(String rightId, String productName,
                                               Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);

}
