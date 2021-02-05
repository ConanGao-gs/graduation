package com.example.demo.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.user.mapper.RoleMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 高爽
 * @since 2021-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_user")
public class User implements Serializable, UserDetails {

    @Autowired
    private RoleMapper roleMapper;

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色列表
     */
    private List<Role> roles = new ArrayList<>();

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return username;
    }

    //下面这四个方法是根据某些条件来判断用户是否可用，方便起见，可以直接设为true
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() { //指示用户的帐户是否已过期
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {   //指示用户是否被锁定或解锁
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {  //指示用户的凭据（密码）是否已过期
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {    //指示用户是否被启用或禁用
        return true;
    }
}
