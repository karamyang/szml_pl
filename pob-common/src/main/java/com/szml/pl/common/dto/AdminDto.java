package com.szml.pl.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/23
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto implements Serializable {
    private Long id;
    private String username;
}
