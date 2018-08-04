package com.yalonglee.learning.security.controller;

import com.yalonglee.learning.security.vo.form.AclModelForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys/acl_model")
@Api(tags = "菜单相关接口")
public class SysAclModelController {

    @ApiOperation(value = "新增菜单")
    @PostMapping("/acl_model")
    public ResponseEntity<?> saveAclModel(@RequestBody AclModelForm aclModelForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/acl_model/{acl_model_id}")
    public ResponseEntity<?> deleteAclModel(@PathVariable("acl_model_id") Long aclModelId) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "更新菜单")
    @PutMapping("/acl_model/{acl_model_id}")
    public ResponseEntity<?> updateAclModel(@PathVariable("acl_model_id") Long aclModelId, @RequestBody AclModelForm aclModelForm) {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "分页查询菜单")
    @GetMapping("/acl_models")
    public ResponseEntity<?> getAclModels() {
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "菜单详情")
    @GetMapping("/acl_model/{acl_model_id}")
    public ResponseEntity<?> getAclModels(@PathVariable("acl_model_id") Long aclModelId) {
        return ResponseEntity.ok(null);
    }


}
