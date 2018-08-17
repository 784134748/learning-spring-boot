package com.yalonglee.learning.security.service;

import com.yalonglee.learning.security.model.SysUser;
import com.yalonglee.learning.security.vo.form.SysUserForm;
import org.springframework.http.ResponseEntity;

public interface SysUserService {

    ResponseEntity insert(SysUserForm sysUserForm);

    ResponseEntity deleteByPrimaryKey(Long id);

    ResponseEntity update(SysUserForm sysUserForm);

    ResponseEntity selectByPrimaryKey(Long id);

    ResponseEntity selectByQuery(SysUser sysUser);

}
