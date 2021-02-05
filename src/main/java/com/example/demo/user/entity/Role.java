package com.example.demo.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

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
@TableName("gs_role")
public class Role implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 如果授予的权限可以当作一个String的话，就可以返回一个String
     * @return
     */
    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }
}
