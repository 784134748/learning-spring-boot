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
    public ResponseEntity<?> deleteAcl(@PathVariable("acl_id") Long aclId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新权限")
    @PutMapping("/acl/{acl_id}")
    public ResponseEntity<?> updateAcl(@PathVariable("acl_id") Long aclId, @RequestBody AclForm aclForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询权限")
    @GetMapping("/acls")
    public ResponseEntity<?> getAcls() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "权限详情")
    @GetMapping("/acl/{acl_id}")
    public ResponseEntity<?> getAcls(@PathVariable("acl_id") Long aclId) {
        return ResponseEntity.ok(null);
    }

}
