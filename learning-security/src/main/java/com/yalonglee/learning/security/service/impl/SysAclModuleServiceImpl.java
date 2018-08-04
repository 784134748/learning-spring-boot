package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.security.mapper.SysAclModuleMapper;
import com.yalonglee.learning.security.service.SysAclModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAclModuleServiceImpl implements SysAclModuleService {

    @Autowired
    @SuppressWarnings("all")
    private SysAclModuleMapper sysAclModuleMapper;
}
