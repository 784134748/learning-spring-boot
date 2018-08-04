package com.yalonglee.learning.security.controller;

import com.yalonglee.learning.security.vo.form.UserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys/user")
@Api(tags = "用户相关接口")
public class SysUserController {

    @ApiOperation(value = "新增角色")
    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserForm userForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/user/{user_id}")
    public ResponseEntity<?> updateUser(@PathVariable("user_id") Long userId, @RequestBody UserForm userForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询角色")
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "角色详情")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getUsers(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(null);
    }
}
