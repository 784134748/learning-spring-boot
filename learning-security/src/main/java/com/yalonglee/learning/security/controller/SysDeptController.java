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
    public ResponseEntity<?> deleteDeptById(@PathVariable("dept_id") Long deptId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新部门")
    @PutMapping("/dept/{dept_id}")
    public ResponseEntity<?> updateDeptById(@PathVariable("dept_id") Long deptId, @RequestBody DeptForm deptForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询部门")
    @GetMapping("/dept/list")
    public ResponseEntity<?> getDeptList() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "部门详情")
    @GetMapping("/dept/{dept_id}")
    public ResponseEntity<?> getDeptById(@PathVariable("dept_id") Long deptId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取部门树")
    @GetMapping("/dept/tree")
    public ResponseEntity<?> getDeptTree() {
        return ResponseEntity.ok(null);
    }
}
