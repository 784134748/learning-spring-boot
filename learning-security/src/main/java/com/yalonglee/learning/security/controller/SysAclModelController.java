package com.yalonglee.learning.security.controller;

import com.yalonglee.learning.security.vo.form.AclModelForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys/acl_model")
@Api(tags = "权限模块相关接口")
public class SysAclModelController {

    @ApiOperation(value = "新增权限模块")
    @PostMapping("/acl_model")
    public ResponseEntity<?> saveAclModel(@RequestBody AclModelForm aclModelForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "删除权限模块")
    @DeleteMapping("/acl_model/{acl_model_id}")
    public ResponseEntity<?> deleteAclModelById(@PathVariable("acl_model_id") Long aclModelId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新权限模块")
    @PutMapping("/acl_model/{acl_model_id}")
    public ResponseEntity<?> updateAclModelById(@PathVariable("acl_model_id") Long aclModelId, @RequestBody AclModelForm aclModelForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询权限模块")
    @GetMapping("/acl_model/list")
    public ResponseEntity<?> getAclModelList() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "权限模块详情")
    @GetMapping("/acl_model/{acl_model_id}")
    public ResponseEntity<?> getAclModelById(@PathVariable("acl_model_id") Long aclModelId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取权限模块树")
    @GetMapping("/acl_model/tree")
    public ResponseEntity<?> getAclModelTree(){
        return ResponseEntity.ok(null);
    }

}
