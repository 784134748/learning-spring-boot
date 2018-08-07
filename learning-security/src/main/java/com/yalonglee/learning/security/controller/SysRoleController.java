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
    public ResponseEntity<?> deleteRoleById(@PathVariable("role_id") Long roleId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/role/{role_id}")
    public ResponseEntity<?> updateRoleById(@PathVariable("role_id") Long roleId, @RequestBody RoleForm roleForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询角色")
    @GetMapping("/role/list")
    public ResponseEntity<?> getRoleList() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "角色详情")
    @GetMapping("/role/{role_id}")
    public ResponseEntity<?> getRoleById(@PathVariable("role_id") Long roleId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取角色树")
    @GetMapping("/role/tree")
    public ResponseEntity<?> getRoleTree() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "修改当前角色对应的权限列表")
    @PutMapping("/role/{role_id}/acl/list")
    public ResponseEntity<?> changeAclListByRoleId(@PathVariable("role_id") Long roleId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "修改拥有当前角色的用户列表")
    @PutMapping("/role/{role_id}/user/list")
    public ResponseEntity<?> changeUserListByRoleId(@PathVariable("role_id") Long roleId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取拥有当前角色的用户列表")
    @GetMapping("/role/{role_id}/user/list")
    public ResponseEntity<?> getUserListByRoleId(@PathVariable("role_id") Long roleId) {
        return ResponseEntity.ok(null);
    }

}
