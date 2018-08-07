package com.yalonglee.learning.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;

/**
 * Created by stephan on 20.03.16.
 */
@ApiModel(value = "JwtUser", description = "登录用户信息")
public class JwtUser implements UserDetails {

    /**
     * 用户名ID
     */
    private final Long id;

    @ApiModelProperty(value = "用户名", position = 1, example = "super admin")
    private final String username;

    @ApiModelProperty(value = "姓", position = 2, example = "super")
    private final String firstname;

    @ApiModelProperty(value = "名", position = 3, example = "admin")
    private final String lastname;

    @ApiModelProperty(value = "密码", position = 4, example = "admin")
    private final String password;

    @ApiModelProperty(value = "邮箱", position = 5, example = "142141414@qq.com")
    private final String email;

    @ApiModelProperty(value = "角色/权限", position = 6)
    private final Collection<? extends GrantedAuthority> authorities;

    @ApiModelProperty(value = "是否有效", position = 7, example = "true")
    private final boolean enabled;

    @ApiModelProperty(value = "最近密码重置日期", position = 8, example = "2018-10-11 00:00:00")
    private final Date lastPasswordResetDate;

    public JwtUser(
            Long id,
            String username,
            String firstname,
            String lastname,
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Date lastPasswordResetDate
    ) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
