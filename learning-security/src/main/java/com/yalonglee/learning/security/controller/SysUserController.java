package com.yalonglee.learning.security.controller;

import com.yalonglee.learning.security.service.SysUserService;
import com.yalonglee.learning.security.vo.form.SysUserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys/user")
@Api(tags = "用户相关接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "新增角色")
    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody SysUserForm sysUserForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/user/{user_id}")
    public ResponseEntity<?> updateUserById(@PathVariable("user_id") Long userId, @RequestBody SysUserForm sysUserForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询角色")
    @GetMapping("/user/list")
    public ResponseEntity<?> getUserList() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "角色详情")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取当前用户拥有的权限列表")
    @GetMapping("/user/{user_id}/acl/list")
    public ResponseEntity<?> getAclListByUserId(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(null);
    }
}
