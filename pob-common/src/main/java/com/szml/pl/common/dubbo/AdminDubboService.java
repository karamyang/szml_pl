package com.szml.pl.common.dubbo;

import com.szml.pl.common.response.ObjectResult;

/**
 * @description: 用户接口服务
 * @author：wufengning
 * @date: 2023/10/23
 */
public interface AdminDubboService {
    /**
     * 根据用户名称获取id
     */
    ObjectResult getIdByName(String username);
}
