package com.szml.pl.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szml.pl.dao.ProductCategoryDao;
import com.szml.pl.dto.CategoryDto;
import com.szml.pl.entity.ProductCategory;
import com.szml.pl.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryDao, ProductCategory> implements ProductCategoryService {
    @Resource
    private ProductCategoryDao categoryDao;
    private Map<Long,ProductCategory> categoryMap=new HashMap<>();
    @Override
    public List<CategoryDto> queryCategoryList() {
         List<CategoryDto> categoryDtoList=new ArrayList<>();
        //1.查询所有的商品类别
        List<ProductCategory> productCategories = categoryDao.queryCategoryList();
        for (ProductCategory productCategory : productCategories) {
            //2.将层级为1的节点存储到Map中
            if(productCategory.getCategoryLevel().equals(1L)){
                categoryMap.put(productCategory.getId(),productCategory);
            }
            else break;
        }
        //根据map中的节点去生成dto对象
        for (Long categoryId : categoryMap.keySet()) {
            CategoryDto categoryDto=new CategoryDto();
            BeanUtils.copyProperties(categoryMap.get(categoryId),categoryDto);
            List<ProductCategory> categoryList=new ArrayList<>();
            categoryDto.setCategoryList(categoryList);
            for (ProductCategory productCategory : productCategories) {
                if(productCategory.getParentId().equals(categoryId)){
                    categoryDto.getCategoryList().add(productCategory);
                }
            }
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }
}
