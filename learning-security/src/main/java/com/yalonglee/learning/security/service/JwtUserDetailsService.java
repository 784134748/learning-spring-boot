package com.yalonglee.learning.security.service;

import com.yalonglee.learning.security.JwtUserFactory;
import com.yalonglee.learning.security.model.security.Authority;
import com.yalonglee.learning.security.model.security.AuthorityName;
import com.yalonglee.learning.security.model.security.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO 通过用户名查询用户信息接口
        Authority authority = new Authority();
        authority.setId(1L);
        authority.setName(AuthorityName.ROLE_ADMIN);
        Authority authority1 = new Authority();
        authority1.setId(2L);
        authority1.setName(AuthorityName.ROLE_USER);
        List<Authority> list = new ArrayList<>();
        list.add(authority);
        list.add(authority1);
        User user = new User();
        user.setUsername("admin");
        user.setPassword("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi");
        user.setFirstname("admin");
        user.setLastname("admin");
        user.setEmail("admin@admin.com");
        user.setEnabled(true);
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
        user.setAuthorities(list);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
