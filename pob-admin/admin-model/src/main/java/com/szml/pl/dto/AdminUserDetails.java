package com.szml.pl.dto;

import com.alibaba.fastjson.JSON;
import com.szml.pl.entity.Admin;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 * Created by macro on 2018/4/26.
 */
public class AdminUserDetails implements UserDetails {
    private Admin admin;
    private String role;
    private List<String> permissionList;
    public AdminUserDetails(Admin umsAdmin,String role, List<String> permissionList) {
        this.role=role;
        this.admin = umsAdmin;
        this.permissionList = permissionList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .map(permission ->new SimpleGrantedAuthority(permission))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getId()+","+admin.getUsername()+","+role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
