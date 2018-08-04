package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.security.mapper.SysLogMapper;
import com.yalonglee.learning.security.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    @SuppressWarnings("all")
    private SysLogMapper sysLogMapper;
}
