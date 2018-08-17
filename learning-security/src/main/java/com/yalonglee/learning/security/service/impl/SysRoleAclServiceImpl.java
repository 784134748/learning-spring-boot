package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.core.common.Result;
import com.yalonglee.learning.security.mapper.SysRoleAclMapper;
import com.yalonglee.learning.security.model.SysRoleAcl;
import com.yalonglee.learning.security.vo.form.SysRoleAclForm;
import com.yalonglee.learning.security.service.SysRoleAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Autowired
    @SuppressWarnings("all")
    private SysRoleAclMapper sysRoleAclMapper;

    public Map<String, Object> getParams(Object o) {
        return BeanMap.create(o);
    }

    @Override
    public ResponseEntity insert(SysRoleAclForm sysRoleAclForm) {
        if (sysRoleAclForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        sysRoleAclMapper.insert(getParams(sysRoleAclForm));
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity deleteByPrimaryKey(Long id) {
        if (id == null || id < 1L) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysRoleAclMapper.deleteByPrimaryKey(id);
        if (affectedRows < 1) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok().body(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity update(SysRoleAclForm sysRoleAclForm) {
        if (sysRoleAclForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysRoleAclMapper.update(getParams(sysRoleAclForm));
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
        SysRoleAcl sysRoleAcl = sysRoleAclMapper.selectByPrimaryKey(id);
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysRoleAcl);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity selectByQuery(SysRoleAcl sysRoleAcl) {
        if (sysRoleAcl == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        List<SysRoleAcl> sysRoleAclList = sysRoleAclMapper.selectByQuery(getParams(sysRoleAcl));
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysRoleAclList);
        return ResponseEntity.ok(result);
    }

}
