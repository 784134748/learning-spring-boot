package com.yalonglee.learning.security.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SysAclMapperTest {


    @Autowired
    @SuppressWarnings("all")
    private SysAclMapper sysAclMapper;

    @Test
    void selectByPrimaryKey() {

    }

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void count() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }
}
