package com.gov.risk.auth.security.service.impl;

import com.gov.risk.auth.sys.entity.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

/**
 * 登录用户信息
 *
 */
@Data
public class UserPrincipal implements UserDetails {

    /**
     * ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Boolean enabled;
    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public UserPrincipal(UsersEntity user) {
        this.setUserId(user.getUserId());
        this.setUsername(user.getUserName());
        this.setPassword(user.getPassword());
        this.setEnabled(user.getStatus() == 1);
        if (user.getRole() != null) {
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.enabled;
    }

}