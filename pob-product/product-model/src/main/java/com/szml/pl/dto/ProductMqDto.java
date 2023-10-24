package com.szml.pl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @description:
 * @author：karma
 * @date: 2023/10/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductMqDto {
    private Long id;
    private Timestamp onlineTime;
}
