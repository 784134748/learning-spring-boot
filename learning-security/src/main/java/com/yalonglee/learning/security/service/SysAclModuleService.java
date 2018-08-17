package com.yalonglee.learning.security.service;

import com.yalonglee.learning.security.model.SysAclModule;
import com.yalonglee.learning.security.vo.form.SysAclModuleForm;
import org.springframework.http.ResponseEntity;

public interface SysAclModuleService {

    ResponseEntity insert(SysAclModuleForm sysAclModuleForm);

    ResponseEntity deleteByPrimaryKey(Long id);

    ResponseEntity update(SysAclModuleForm sysAclModuleForm);

    ResponseEntity selectByPrimaryKey(Long id);

    ResponseEntity selectByQuery(SysAclModule sysAclModule);

}
