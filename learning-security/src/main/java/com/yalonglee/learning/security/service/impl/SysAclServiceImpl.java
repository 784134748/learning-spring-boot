package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.security.mapper.SysAclMapper;
import com.yalonglee.learning.security.service.SysAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAclServiceImpl implements SysAclService {

    @Autowired
    @SuppressWarnings("all")
    private SysAclMapper sysAclMapper;
}