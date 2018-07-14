package com.yalonglee.learning.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yalonglee.learning.*")
public class LearningCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningCoreApplication.class, args);
    }
}
