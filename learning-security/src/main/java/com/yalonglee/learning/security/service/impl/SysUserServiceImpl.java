package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.core.common.Result;
import com.yalonglee.learning.security.mapper.SysUserMapper;
import com.yalonglee.learning.security.model.SysUser;
import com.yalonglee.learning.security.vo.form.SysUserForm;
import com.yalonglee.learning.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    @SuppressWarnings("all")
    private SysUserMapper sysUserMapper;

    public Map<String, Object> getParams(Object o) {
        return BeanMap.create(o);
    }

    @Override
    public ResponseEntity insert(SysUserForm sysUserForm) {
        if (sysUserForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        sysUserMapper.insert(getParams(sysUserForm));
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity deleteByPrimaryKey(Long id) {
        if (id == null || id < 1L) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysUserMapper.deleteByPrimaryKey(id);
        if (affectedRows < 1) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok().body(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity update(SysUserForm sysUserForm) {
        if (sysUserForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysUserMapper.update(getParams(sysUserForm));
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
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysUser);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity selectByQuery(SysUser sysUser) {
        if (sysUser == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        List<SysUser> sysUserList = sysUserMapper.selectByQuery(getParams(sysUser));
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysUserList);
        return ResponseEntity.ok(result);
    }

}
