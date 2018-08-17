package com.yalonglee.learning.security.service;

import com.yalonglee.learning.security.model.SysAcl;
import com.yalonglee.learning.security.vo.form.SysAclForm;
import org.springframework.http.ResponseEntity;

public interface SysAclService {

    ResponseEntity insert(SysAclForm sysAclForm);

    ResponseEntity deleteByPrimaryKey(Long id);

    ResponseEntity update(SysAclForm sysAclForm);

    ResponseEntity selectByPrimaryKey(Long id);

    ResponseEntity selectByQuery(SysAcl sysAcl);

}
