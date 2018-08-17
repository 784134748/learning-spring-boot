package com.yalonglee.learning.security.service;

import com.yalonglee.learning.security.model.SysRoleAcl;
import com.yalonglee.learning.security.vo.form.SysRoleAclForm;
import org.springframework.http.ResponseEntity;

public interface SysRoleAclService {

    ResponseEntity insert(SysRoleAclForm sysRoleAclForm);

    ResponseEntity deleteByPrimaryKey(Long id);

    ResponseEntity update(SysRoleAclForm sysRoleAclForm);

    ResponseEntity selectByPrimaryKey(Long id);

    ResponseEntity selectByQuery(SysRoleAcl sysRoleAcl);

}
