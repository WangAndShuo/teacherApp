package com.classproject.teacherapp.vo;

import lombok.Data;

@Data
public class SelectScoreVo {

    /**
     * 班级名称
     */
    private String classId;


    /**
     * 班级名称
     */
    private String className;

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
}
