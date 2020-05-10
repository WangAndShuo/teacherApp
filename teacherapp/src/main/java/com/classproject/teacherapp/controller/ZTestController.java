package com.classproject.teacherapp.controller;

import com.classproject.teacherapp.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @ClassName ZTestController
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/26 9:44
 **/
@RestController
@RequestMapping("/redis")
public class ZTestController {
    private String testString = "testString";
    private String userKey = "userKey";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/add")
    public String add() {
        //1,添加一个Value为String
        stringRedisTemplate.opsForValue().set(testString, "测试存储字符串类型");
        //2,添加一个Value为对象
        AppUser user = new AppUser();
        user.setUuid("123");
        user.setUsername("张三");
        user.setPassword("1111");
        redisTemplate.opsForValue().set(user.getUuid(), user);
        return "成功";
    }

    @GetMapping("/getUser")
    public AppUser findUserByKey() {
        AppUser user = (AppUser) redisTemplate.opsForValue().get(userKey);
        return user;
    }

    @GetMapping("/getString")
    public String findString() {
        String s = stringRedisTemplate.opsForValue().get(testString);
        return s;
    }
    @GetMapping("/delete")
    public String deleteByKey(){
        //1,删除string类型
        stringRedisTemplate.delete(testString);
        //2,删除user对象
        redisTemplate.delete(userKey);
        return "删除成功";
    }




}

