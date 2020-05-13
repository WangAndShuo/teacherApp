package com.classproject.teacherapp.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName CreateJobVo
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/6 17:43
 **/
@Data
public class CreateJobVo {
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
    private String userId;

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
