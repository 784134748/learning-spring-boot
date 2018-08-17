package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.core.common.Result;
import com.yalonglee.learning.security.mapper.SysAclMapper;
import com.yalonglee.learning.security.model.SysAcl;
import com.yalonglee.learning.security.vo.form.SysAclForm;
import com.yalonglee.learning.security.service.SysAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysAclServiceImpl implements SysAclService {

    @Autowired
    @SuppressWarnings("all")
    private SysAclMapper sysAclMapper;

    public Map<String, Object> getParams(Object o) {
        return BeanMap.create(o);
    }

    @Override
    public ResponseEntity insert(SysAclForm sysAclForm) {
        if (sysAclForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        sysAclMapper.insert(getParams(sysAclForm));
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity deleteByPrimaryKey(Long id) {
        if (id == null || id < 1L) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysAclMapper.deleteByPrimaryKey(id);
        if (affectedRows < 1) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok().body(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity update(SysAclForm sysAclForm) {
        if (sysAclForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysAclMapper.update(getParams(sysAclForm));
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
        SysAcl sysAcl = sysAclMapper.selectByPrimaryKey(id);
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysAcl);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity selectByQuery(SysAcl sysAcl) {
        if (sysAcl == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        List<SysAcl> sysAclList = sysAclMapper.selectByQuery(getParams(sysAcl));
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysAclList);
        return ResponseEntity.ok(result);
    }

}
