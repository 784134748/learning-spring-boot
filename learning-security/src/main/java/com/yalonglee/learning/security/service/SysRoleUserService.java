package com.yalonglee.learning.security.service;

import com.yalonglee.learning.security.model.SysRoleUser;
import com.yalonglee.learning.security.vo.form.SysRoleUserForm;
import org.springframework.http.ResponseEntity;

public interface SysRoleUserService {

    ResponseEntity insert(SysRoleUserForm sysRoleUserForm);

    ResponseEntity deleteByPrimaryKey(Long id);

    ResponseEntity update(SysRoleUserForm sysRoleUserForm);

    ResponseEntity selectByPrimaryKey(Long id);

    ResponseEntity selectByQuery(SysRoleUser sysRoleUser);

}
