package com.szml.pl.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @description: RPC远程调用使用
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
    private String password;
    private List<String> permissionList;
}
