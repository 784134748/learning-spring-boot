package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.core.common.Result;
import com.yalonglee.learning.security.mapper.SysRoleUserMapper;
import com.yalonglee.learning.security.model.SysRoleUser;
import com.yalonglee.learning.security.vo.form.SysRoleUserForm;
import com.yalonglee.learning.security.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    @SuppressWarnings("all")
    private SysRoleUserMapper sysRoleUserMapper;

    public Map<String, Object> getParams(Object o) {
        return BeanMap.create(o);
    }

    @Override
    public ResponseEntity insert(SysRoleUserForm sysRoleUserForm) {
        if (sysRoleUserForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        sysRoleUserMapper.insert(getParams(sysRoleUserForm));
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity deleteByPrimaryKey(Long id) {
        if (id == null || id < 1L) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysRoleUserMapper.deleteByPrimaryKey(id);
        if (affectedRows < 1) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok().body(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity update(SysRoleUserForm sysRoleUserForm) {
        if (sysRoleUserForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysRoleUserMapper.update(getParams(sysRoleUserForm));
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
        SysRoleUser sysRoleUser = sysRoleUserMapper.selectByPrimaryKey(id);
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysRoleUser);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity selectByQuery(SysRoleUser sysRoleUser) {
        if (sysRoleUser == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        List<SysRoleUser> sysRoleUserList = sysRoleUserMapper.selectByQuery(getParams(sysRoleUser));
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysRoleUserList);
        return ResponseEntity.ok(result);
    }

}
