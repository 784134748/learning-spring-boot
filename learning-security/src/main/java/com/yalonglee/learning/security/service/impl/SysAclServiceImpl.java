package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.security.mapper.SysAclMapper;
import com.yalonglee.learning.security.model.SysAcl;
import com.yalonglee.learning.security.service.SysAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAclServiceImpl implements SysAclService {

    @Autowired
    private SysAclMapper sysAclMapper;

    @Override
    public List<SysAcl> getAllSysAcl() {
        return sysAclMapper.selectSysAcl();
    }
}
