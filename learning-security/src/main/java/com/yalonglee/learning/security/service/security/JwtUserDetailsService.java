package com.yalonglee.learning.security.service.security;

import com.google.common.collect.Lists;
import com.yalonglee.learning.security.JwtUserFactory;
import com.yalonglee.learning.security.model.security.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO 通过用户名查询用户信息接口

        User user = User.builder()
                .id(1L)
                .username("admin")
                .password("admin")
                .enabled(true)
                .lastPasswordResetDate(new Date(System.currentTimeMillis()))
                .authorities(Lists.newArrayList())
                .build();

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
