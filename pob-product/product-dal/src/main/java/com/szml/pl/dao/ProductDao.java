package com.szml.pl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szml.pl.dto.ProductDto;
import com.szml.pl.entity.Product;
import com.szml.pl.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {
    Product selectProductById(Long id);

    List<Product> selectProductFromUser(String rightId, String productName,
                                        Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);

    List<Product> selectProductFromAdmin(String rightId, String productName,
                                         Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId);

    List<Product> selectProductAndProductAgentFromUser(String rightId, String productName,
                                                       Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId,Long adminId);
    List<Product> selectProductAndProductAgentFromAdmin(String rightId, String productName,
                                                        Timestamp onlineTime, Timestamp lineTime, Integer status, Long manageUserId,Long adminId);
}
