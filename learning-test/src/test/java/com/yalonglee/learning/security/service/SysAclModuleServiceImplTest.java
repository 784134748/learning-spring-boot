package com.yalonglee.learning.security.service;

import com.yalonglee.learning.initial.LearningInitialApplication;
import com.yalonglee.learning.initial.config.SecurityConfig;
import com.yalonglee.learning.initial.config.Swagger2Config;
import com.yalonglee.learning.security.model.SysAclModule;
import com.yalonglee.learning.security.vo.form.SysAclModuleForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LearningInitialApplication.class, Swagger2Config.class, SecurityConfig.class})
public class SysAclModuleServiceImplTest {

    @Autowired
    private SysAclModuleService sysAclModuleService;

    @Test
    @Rollback
    public void selectByPrimaryKey() {
        sysAclModuleService.selectByPrimaryKey(1L);
    }

    @Test
    @Rollback
    public void selectByQuery() {
        SysAclModule sysAclModule = SysAclModule.builder()
                .id(1L)
                .build();
        sysAclModuleService.selectByQuery(sysAclModule);
    }

    @Test
    @Rollback
    public void deleteByPrimaryKey() {
        sysAclModuleService.deleteByPrimaryKey(1L);
    }

    @Test
    @Rollback
    public void insert() {
        SysAclModuleForm sysAclModuleForm = SysAclModuleForm.builder()
                .id(1L)
                .build();
        sysAclModuleService.insert(sysAclModuleForm);
    }

    @Test
    @Rollback
    public void update() {
        SysAclModuleForm sysAclModuleForm = SysAclModuleForm.builder()
                .id(1L)
                .build();
        sysAclModuleService.update(sysAclModuleForm);
    }
}
