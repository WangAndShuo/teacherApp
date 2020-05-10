package com.classproject.teacherapp.entity;

import com.classproject.teacherapp.util.ExcelBody;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-03-22
 */
@Data
public class AppComJob implements ExcelBody {
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
     * 提交人
     */
    private String stuId;

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


    /**
     * 提交时间
     */
    private String commit_time;

    /**
     * 关联作业id
     */
    private String  cre_job_id;

    @Override
    public void setExcel(Row row) {


    }
}