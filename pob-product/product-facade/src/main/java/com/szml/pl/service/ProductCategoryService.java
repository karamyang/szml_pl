package com.szml.pl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szml.pl.dto.CategoryDto;
import com.szml.pl.entity.ProductCategory;

import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    List<CategoryDto> queryCategoryList();
}
