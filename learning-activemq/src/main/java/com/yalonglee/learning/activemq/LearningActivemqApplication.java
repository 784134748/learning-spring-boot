package com.yalonglee.learning.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.yalonglee.learning")
public class LearningActivemqApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningActivemqApplication.class, args);
    }

}
