package com.szml.pl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szml.pl.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

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

    /**
     * 更新修改时间
     */
    int updateTime(Long id, Timestamp updateTime);

    /**
     * 根据用户名查询权限
     * @param username
     * @return
     */
    List<String> getPermissions(@Param("username") String username);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    String getRole(@Param("username") String username);
}
