package com.szml.pl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto implements Serializable {
    private Long id;
    private String productName;
    private String type;
    private Long categoryId;
    private String rightId;
    private String exchangeType;
    private Double cash;
    private Long integral;
    private String mixed;
    private Long stock;
    private Long restriction;
    private String imgUrl;
    private String description;
    private String detail;
    private String blackListId;
    private String whiteListId;
    private Timestamp onlineTime;
    private Timestamp lineTime;
    private Integer status;
    private Long createUserId;
    private Long manageUserId;
    private String notShipments;
    private Long flag;
}
