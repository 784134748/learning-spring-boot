package com.yalonglee.learning.security.service;

import com.yalonglee.learning.security.model.SysLog;
import com.yalonglee.learning.security.vo.form.SysLogForm;
import org.springframework.http.ResponseEntity;

public interface SysLogService {

    ResponseEntity insert(SysLogForm sysLogForm);

    ResponseEntity deleteByPrimaryKey(Long id);

    ResponseEntity update(SysLogForm sysLogForm);

    ResponseEntity selectByPrimaryKey(Long id);

    ResponseEntity selectByQuery(SysLog sysLog);

}
