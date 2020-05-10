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
public class AppCreJob {
    /**
     * 主键
     */
    private String uuid;

    /**
     * 作业名称
     */
    private String jobName;

    /**
     * 作业详细信息
     */
    private String jobInfo;

    /**
     * 发布人
     */
    private String user;

    /**
     * 发布班级id
     */
    private String className;

    /**
     * 发布班级id
     */
    private String classId;

    /**
     * 创建时间
     */
    private String creTime;

    /**
     * 结束时间
     */
    private String finTime;

    /**
     * url地址
     */
    private String address;


}