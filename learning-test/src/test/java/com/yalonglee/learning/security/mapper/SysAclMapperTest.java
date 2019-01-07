package com.yalonglee.learning.security.mapper;

import com.yalonglee.learning.security.model.SysAclModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SysAclMapperTest {


    @Autowired
    @SuppressWarnings("all")
    private SysAclMapper sysAclMapper;

    @Test
    @Rollback
    public void testSysAclMapper() {
        SysAclModel sysAclModel = SysAclModel.builder()
                .name("管理员")
                .code("admin")
                .operator("admin")
                .operateTime(LocalDateTime.now())
                .operateIp("1.1.1.1")
                .aclModuleId(1)
                .seq(1)
                .status(1)
                .type(1)
                .remark("")
                .url("/")
                .build();
        sysAclMapper.insert(sysAclModel);
        sysAclMapper.fullUpdate(sysAclModel);
        sysAclMapper.incUpdate(sysAclModel);
        sysAclMapper.count(sysAclModel);
    }
}
