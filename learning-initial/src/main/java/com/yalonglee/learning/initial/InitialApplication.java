package com.yalonglee.learning.initial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class InitialApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LearningInitialApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InitialApplication.class, args);
    }

}
