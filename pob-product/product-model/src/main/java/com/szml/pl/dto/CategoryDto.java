package com.szml.pl.dto;

import com.szml.pl.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String categoryName;
    private Long parentId;
    private Long categoryLevel;
    private Long status;
    private String imgUrl;
    private Long leaf;
    private Long priority;

    private List<ProductCategory> categoryList;

}
