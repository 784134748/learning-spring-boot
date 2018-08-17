package com.yalonglee.learning.security.service;

import com.yalonglee.learning.security.model.SysDept;
import com.yalonglee.learning.security.vo.form.SysDeptForm;
import org.springframework.http.ResponseEntity;

public interface SysDeptService {

    ResponseEntity insert(SysDeptForm sysDeptForm);

    ResponseEntity deleteByPrimaryKey(Long id);

    ResponseEntity update(SysDeptForm sysDeptForm);

    ResponseEntity selectByPrimaryKey(Long id);

    ResponseEntity selectByQuery(SysDept sysDept);

}
