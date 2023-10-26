package com.szml.pl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductDraft;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Mapper
public interface ProductDraftDao extends BaseMapper<ProductDraft> {
    Page<ProductDraft> selectProductDraftFromUser(Page<Product> page, String rightId, String productName,
                                                  Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);
    Page<ProductDraft> selectProductDraftFromAdmin(Page<Product> page,String rightId, String productName,
                                                   Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);
}
