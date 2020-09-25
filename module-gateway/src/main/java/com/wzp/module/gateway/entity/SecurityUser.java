package com.wzp.module.gateway.entity;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.PageResult;
import com.wzp.module.core.pojo.Role;
import lombok.Data;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sun.security.util.AuthResources_it;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/14
 */

@Data
public class SecurityUser implements UserDetails, Serializable {

    private String loginId;
    private Long phone;
    private String mail;
    private String password;
    private String address;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorityList().stream().map(authority -> (GrantedAuthority) authority::getPath).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    // 永不过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 永不锁定
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

    // 是否是超级管理员
    public boolean isSuperAdmin() {
        return role.isSuperAdmin();
    }


}
