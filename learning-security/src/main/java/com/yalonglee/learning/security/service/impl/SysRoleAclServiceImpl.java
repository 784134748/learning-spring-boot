package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.security.mapper.SysRoleMapper;
import com.yalonglee.learning.security.service.SysRoleAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Autowired
    @SuppressWarnings("all")
    private SysRoleMapper sysRoleMapper;
}
