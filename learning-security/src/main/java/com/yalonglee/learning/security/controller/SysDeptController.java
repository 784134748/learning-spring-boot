package com.yalonglee.learning.security.controller;

import com.yalonglee.learning.security.vo.form.DeptForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sys/dept")
@Api(tags = "部门相关接口")
public class SysDeptController {

    @ApiOperation(value = "新增部门")
    @PostMapping("/dept")
    public ResponseEntity<?> saveDept(@RequestBody DeptForm deptForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/dept/{dept_id}")
    public ResponseEntity<?> deleteDept(@PathVariable("dept_id") Long deptId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新部门")
    @PutMapping("/dept/{dept_id}")
    public ResponseEntity<?> updateDept(@PathVariable("dept_id") Long deptId, @RequestBody DeptForm deptForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询部门")
    @GetMapping("/depts")
    public ResponseEntity<?> getDepts() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "部门详情")
    @GetMapping("/dept/{dept_id}")
    public ResponseEntity<?> getDepts(@PathVariable("dept_id") Long deptId) {
        return ResponseEntity.ok(null);
    }

}
