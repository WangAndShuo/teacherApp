package com.classproject.teacherapp.entity;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-03-22
 */
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
    private String userId;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo == null ? null : jobInfo.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCreTime() {
        return creTime;
    }

    public void setCreTime(String creTime) {
        this.creTime = creTime == null ? null : creTime.trim();
    }

    public String getFinTime() {
        return finTime;
    }

    public void setFinTime(String finTime) {
        this.finTime = finTime == null ? null : finTime.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}