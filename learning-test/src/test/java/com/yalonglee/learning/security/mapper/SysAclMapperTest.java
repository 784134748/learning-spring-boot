package com.yalonglee.learning.security.mapper;

import com.google.common.collect.Maps;
import com.yalonglee.learning.security.model.SysAcl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SysAclMapperTest {


    @Autowired
    @SuppressWarnings("all")
    private SysAclMapper sysAclMapper;

    @Test
    @Rollback
    public void selectByPrimaryKey() {
        SysAcl sysAcl = sysAclMapper.selectByPrimaryKey(1L);
        Assert.assertNotNull(sysAcl);
    }

    @Test
    @Rollback
    public void selectByQurey() {
        SysAcl sysAcl = SysAcl.builder()
                .id(1L)
                .build();
        Map<String, Object> params = BeanMap.create(sysAcl);
        List<SysAcl> sysAclList = sysAclMapper.selectByQuery(params);
        Assert.assertNotNull(sysAclList);
        Assert.assertTrue(sysAclList.size() > 0);
    }

    @Test
    @Rollback
    public void deleteByPrimaryKey() {
        Integer affectedRows = sysAclMapper.deleteByPrimaryKey(1L);
        Assert.assertTrue(affectedRows > 0);
    }

    @Test
    @Rollback
    public void count() {
        SysAcl sysAcl = SysAcl.builder()
                .id(1L)
                .build();
        Map<String, Object> params = BeanMap.create(sysAcl);
        Integer count = sysAclMapper.count(params);
        Assert.assertTrue(count > 0);
    }

    @Test
    @Rollback
    public void insert() {
        SysAcl sysAcl = SysAcl.builder()
                .aclModuleId(1L)
                .code("admin")
                .name("admin")
                .operateIp("0.0.0.0")
                .operator("张三")
                .seq(1)
                .type(1)
                .url("/")
                .build();
        Map<String, Object> params = BeanMap.create(sysAcl);
        sysAclMapper.insert(params);
        Assert.assertTrue(params.get("id") != null);
    }

    @Test
    @Rollback
    public void update() {
        SysAcl sysAcl = SysAcl.builder()
                .id(1L)
                .aclModuleId(1L)
                .code("admin")
                .name("admin")
                .operateIp("0.0.0.0")
                .operator("张三")
                .seq(1)
                .type(1)
                .url("/")
                .build();
        Map<String, Object> params = BeanMap.create(sysAcl);
        Integer affectedRows = sysAclMapper.update(params);
        Assert.assertTrue(affectedRows > 0);
    }
}
