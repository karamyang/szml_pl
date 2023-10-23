package com.szml.pl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szml.pl.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author：wufengning
 * @date: 2023/10/22
 */
@Mapper
public interface AdminDao extends BaseMapper<Admin> {
    /**
     * 注册用户
     */
    int insertAdmin(Admin admin);
    /**
     * 用户查询
     */
    Admin selectAdminById(Long id);
    Admin selectAdminByUsername(String username);
    Admin selectAdminByEmail(String email);
    Admin selectAdminByTelephone(String telephone);
    /**
     * 修改密码
     */
    int updatePassword(Long id,String password);


}
