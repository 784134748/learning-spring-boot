package com.yalonglee.learning.security.service.impl;

import com.yalonglee.learning.core.common.Result;
import com.yalonglee.learning.security.mapper.SysLogMapper;
import com.yalonglee.learning.security.model.SysLog;
import com.yalonglee.learning.security.vo.form.SysLogForm;
import com.yalonglee.learning.security.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    @SuppressWarnings("all")
    private SysLogMapper sysLogMapper;

    public Map<String, Object> getParams(Object o) {
        return BeanMap.create(o);
    }

    @Override
    public ResponseEntity insert(SysLogForm sysLogForm) {
        if (sysLogForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        sysLogMapper.insert(getParams(sysLogForm));
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity deleteByPrimaryKey(Long id) {
        if (id == null || id < 1L) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysLogMapper.deleteByPrimaryKey(id);
        if (affectedRows < 1) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok().body(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity update(SysLogForm sysLogForm) {
        if (sysLogForm == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        Integer affectedRows = sysLogMapper.update(getParams(sysLogForm));
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
        SysLog sysLog = sysLogMapper.selectByPrimaryKey(id);
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysLog);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity selectByQuery(SysLog sysLog) {
        if (sysLog == null) {
            return ResponseEntity.ok().body(Result.success(HttpStatus.BAD_REQUEST));
        }
        List<SysLog> sysLogList = sysLogMapper.selectByQuery(getParams(sysLog));
        Result result = Result.success(HttpStatus.OK);
        result.setEntry(sysLogList);
        return ResponseEntity.ok(result);
    }

}
