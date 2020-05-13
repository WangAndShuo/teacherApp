package com.classproject.teacherapp.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * app_verify
 * @author 
 */
@Data
public class AppVerify implements Serializable {
    private String uuid;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 大学名称
     */
    private String collegial;

    /**
     * 大学地点
     */
    private String address;

    /**
     * 专业
     */
    private String major;

    /**
     * 教学年龄
     */
    private String workTime;

    /**
     * 工作证
     */
    private String workPermit;

    /**
     * 是否验证完毕 0-验证完毕 1-没有验证完毕 
     */
    private String verification;

    private static final long serialVersionUID = 1L;
}