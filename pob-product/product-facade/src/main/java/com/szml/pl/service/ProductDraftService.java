package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.ProductDraft;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
public interface ProductDraftService extends IService<ProductDraft> {

    Boolean saveDraft(ProductDto productDto);
}
