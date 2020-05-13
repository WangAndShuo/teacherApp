package com.classproject.teacherapp.entity;

import lombok.Data;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-03-22
 */
@Data
public class AppUserinfo {
    private String uuid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private String brithday;

    /**
     * 地址
     */
    private String address;

    /**
     * 学校
     */
    private String school;

    /**
     * 学院
     */
    private String college;

    /**
     * 学历
     */
    private String education;

    /**
     * 年级
     */
    private String grade;

    /**
     * 头像
     */
    private String head;
    /**
     * 是否是学生1-学生 0-老师
     */
    private String teacher;

    }