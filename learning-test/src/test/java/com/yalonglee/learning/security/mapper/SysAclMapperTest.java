package com.yalonglee.learning.security.mapper;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

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
        sysAclMapper.selectByPrimaryKey(1L);
    }

    @Test
    @Rollback
    public void selectByQurey(){
        sysAclMapper.selectByQuery(Maps.newHashMap());
    }

    @Test
    @Rollback
    public void deleteByPrimaryKey() {
        sysAclMapper.deleteByPrimaryKey(1L);
    }

    @Test
    @Rollback
    public void count() {
        sysAclMapper.count(Maps.newHashMap());
    }

    @Test
    @Rollback
    public void insert() {
        sysAclMapper.insert(Maps.newHashMap());
    }

    @Test
    @Rollback
    public void update() {
        sysAclMapper.update(Maps.newHashMap());
    }
}
