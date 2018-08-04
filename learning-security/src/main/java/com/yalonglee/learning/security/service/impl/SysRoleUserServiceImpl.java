package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.security.mapper.SysRoleUserMapper;
import com.yalonglee.learning.security.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    @SuppressWarnings("all")
    private SysRoleUserMapper sysRoleUserMapper;
}
