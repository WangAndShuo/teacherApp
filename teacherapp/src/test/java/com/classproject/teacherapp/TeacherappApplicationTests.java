package com.classproject.teacherapp;

import com.classproject.teacherapp.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TeacherappApplicationTests {
    @Autowired
    UserController userController;
    @Test
    void contextLoads() {
        userController.register("1234", "1234");
    }


}
