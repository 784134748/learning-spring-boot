package com.yalonglee.learning.security.model.security;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    private Boolean enabled;

    private Date lastPasswordResetDate;

    private List<Authority> authorities;

}