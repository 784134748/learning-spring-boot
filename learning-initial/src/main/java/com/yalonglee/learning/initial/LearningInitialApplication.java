package com.yalonglee.learning.initial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yalonglee.learning")
public class LearningInitialApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningInitialApplication.class, args);
    }
}
