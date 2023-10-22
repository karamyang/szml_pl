package com.szml.pl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {


}
