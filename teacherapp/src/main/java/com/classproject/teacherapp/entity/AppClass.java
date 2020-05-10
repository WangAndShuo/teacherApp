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
public class AppClass {
    private String uuid;

    /**
     * 课程名
     */
    private String classname;

    /**
     * 老师姓名
     */
    private String teacher;

    /**
     * 房间地址
     */
    private String room;

    /**
     * 第几周
     */
    private String week;

    /**
     * 节数
     */
    private String time;

    /**
     * 用户id
     */
    private String userId;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}