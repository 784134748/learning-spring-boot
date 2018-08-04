package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.security.mapper.SysUserMapper;
import com.yalonglee.learning.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    @SuppressWarnings("all")
    private SysUserMapper sysUserMapper;
}
