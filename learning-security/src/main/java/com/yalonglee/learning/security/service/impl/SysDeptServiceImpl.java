package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.security.mapper.SysDeptMapper;
import com.yalonglee.learning.security.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    @SuppressWarnings("all")
    private SysDeptMapper sysDeptMapper;
}
