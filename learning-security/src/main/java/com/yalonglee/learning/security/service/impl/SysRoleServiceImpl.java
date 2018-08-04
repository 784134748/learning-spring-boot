package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.security.mapper.SysRoleMapper;
import com.yalonglee.learning.security.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    @SuppressWarnings("all")
    private SysRoleMapper sysRoleMapper;
}
