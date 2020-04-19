package com.classproject.teacherapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan("com.classproject.teacherapp.dao")
public class TeacherappApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TeacherappApplication.class, args);
    }


}
