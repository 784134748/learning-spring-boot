package com.yalonglee.learning.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yalonglee.learning.account.*"})
@MapperScan(value = "com.yalonglee.learning.account.mapper")
public class LearningAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningAccountApplication.class, args);
    }

}
