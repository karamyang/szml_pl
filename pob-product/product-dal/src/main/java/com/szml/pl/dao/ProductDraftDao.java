package com.szml.pl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    List<ProductDraft> selectProductDraftFromUser(String rightId, String productName,
                                                  Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);
    List<ProductDraft> selectProductDraftFromAdmin(String rightId, String productName,
                                                   Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);
}
