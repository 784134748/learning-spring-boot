package com.yalonglee.learning.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by stephan on 20.03.16.
 */
@ApiModel(value = "JwtAuthenticationRequest", description = "JwtAuthenticationRequest")
public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    @ApiModelProperty(value = "用户名", example = "admin", position = 1, required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(value = "密码", example = "admin", position = 2, required = true)
    @NotBlank
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
