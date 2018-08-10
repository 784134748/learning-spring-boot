package com.yalonglee.learning.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yalonglee.learning")
public class LearningTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningTestApplication.class, args);
    }
}
