package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.core.common.Result;
import com.yalonglee.learning.security.mapper.SysAclModuleMapper;
import com.yalonglee.learning.security.model.SysAclModule;
import com.yalonglee.learning.security.vo.form.SysAclModuleForm;
import com.yalonglee.learning.security.service.SysAclModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysAclModuleServiceImpl implements SysAclModuleService {

    @Autowired
    @SuppressWarnings("all")
    private SysAclModuleMapper sysAclModuleMapper;

    public Map<String, Object> getParams(Object o) {
        return BeanMap.create(o);
    }

    @Override
    public ResponseEntity insert(SysAclModuleForm sysAclModuleForm) {
        if (sysAclModuleForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        sysAclModuleMapper.insert(getParams(sysAclModuleForm));
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity deleteByPrimaryKey(Long id) {
        if (id == null || id < 1L) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysAclModuleMapper.deleteByPrimaryKey(id);
        if (affectedRows < 1) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok().body(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity update(SysAclModuleForm sysAclModuleForm) {
        if (sysAclModuleForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysAclModuleMapper.update(getParams(sysAclModuleForm));
        if (affectedRows < 1) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok().body(HttpStatus.RESET_CONTENT);
    }

    @Override
    public ResponseEntity selectByPrimaryKey(Long id) {
        if (id == null || id < 1L) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        SysAclModule sysAclModule = sysAclModuleMapper.selectByPrimaryKey(id);
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysAclModule);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity selectByQuery(SysAclModule sysAclModule) {
        if (sysAclModule == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        List<SysAclModule> sysAclModuleList = sysAclModuleMapper.selectByQuery(getParams(sysAclModule));
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysAclModuleList);
        return ResponseEntity.ok(result);
    }

}
