package com.yalonglee.learning.security.controller;

import com.yalonglee.learning.security.vo.form.RoleForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys/role")
@Api(tags = "角色相关接口")
public class SysRoleController {

    @ApiOperation(value = "新增角色")
    @PostMapping("/role")
    public ResponseEntity<?> saveRole(@RequestBody RoleForm roleForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{role_id}")
    public ResponseEntity<?> deleteRole(@PathVariable("role_id") Long roleId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/role/{role_id}")
    public ResponseEntity<?> updateRole(@PathVariable("role_id") Long roleId, @RequestBody RoleForm roleForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询角色")
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "角色详情")
    @GetMapping("/role/{role_id}")
    public ResponseEntity<?> getRoles(@PathVariable("role_id") Long roleId) {
        return ResponseEntity.ok(null);
    }

}
