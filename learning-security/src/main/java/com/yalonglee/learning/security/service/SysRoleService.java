package com.yalonglee.learning.security.service;

import com.yalonglee.learning.security.model.SysRole;
import com.yalonglee.learning.security.vo.form.SysRoleForm;
import org.springframework.http.ResponseEntity;

public interface SysRoleService {

    ResponseEntity insert(SysRoleForm sysRoleForm);

    ResponseEntity deleteByPrimaryKey(Long id);

    ResponseEntity update(SysRoleForm sysRoleForm);

    ResponseEntity selectByPrimaryKey(Long id);

    ResponseEntity selectByQuery(SysRole sysRole);

}
