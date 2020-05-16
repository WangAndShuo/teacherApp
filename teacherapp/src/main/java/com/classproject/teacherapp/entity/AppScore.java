package com.classproject.teacherapp.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * app_score
 * @author 
 */
@Data
public class AppScore implements Serializable {
    private String uuid;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班级名称
     */
    private String classId;

    /**
     * 用户ID
     *
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 年级
     */
    private String grade;

    /**
     * 成绩
     */
    private String score;

    /**
     * 作业id
     */
    private String jobId;

    /**
     * 老师姓名
     */
    private String teacher;

    private static final long serialVersionUID = 1L;
}