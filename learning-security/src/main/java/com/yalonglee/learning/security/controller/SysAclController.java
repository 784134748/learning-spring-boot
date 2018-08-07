package com.yalonglee.learning.security.controller;

import com.yalonglee.learning.security.vo.form.AclForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys/acl")
@Api(tags = "权限相关接口")
public class SysAclController {

    @ApiOperation(value = "新增权限")
    @PostMapping("/acl")
    public ResponseEntity<?> saveAcl(@RequestBody AclForm aclForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "删除权限")
    @DeleteMapping("/acl/{acl_id}")
    public ResponseEntity<?> deleteAclById(@PathVariable("acl_id") Long aclId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新权限")
    @PutMapping("/acl/{acl_id}")
    public ResponseEntity<?> updateAclById(@PathVariable("acl_id") Long aclId, @RequestBody AclForm aclForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询权限")
    @GetMapping("/acl/list")
    public ResponseEntity<?> getAclList() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "权限详情")
    @GetMapping("/acl/{acl_id}")
    public ResponseEntity<?> getAclById(@PathVariable("acl_id") Long aclId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "当前权限被哪些用户所拥有")
    @GetMapping("/acl/{acl_id}/user/list")
    public ResponseEntity<?> getUserListByAclId(@PathVariable("acl_id") Long aclId){
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "当前权限被哪些角色所拥有")
    @GetMapping("/acl/{acl_id}/role/list")
    public ResponseEntity<?> getRoleListByAclId(@PathVariable("acl_id") Long aclId){
        return ResponseEntity.ok(null);
    }

}
