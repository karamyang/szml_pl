package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    Page<ProductDto> findProductDraftFromUser(ProductDto productDto, Long current, Long size);

    Page<ProductDto> findProductDraftFromAdmin(ProductDto productDto, Long current, Long size);
}
