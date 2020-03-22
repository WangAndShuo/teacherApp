package com.classproject.teacherapp.util;


public enum StatusCode {


    Success(0, "成功"),
    Fail(1, "失败"),
    InvalidParams(400, "非法的参数!"),
    UserNotLogin(401, "用户没登录"),
    UserPoolIsNull(20000, "抱歉，今日用户数已被抢完，请您留意下次活动"),
    Activity_Ended(20001, "抱歉，本场活动已结束"),
    System_Error(20002, "系统开小差了"),
    HAVE_JOINED(20003, "您已经参加过本场活动了!"),
    InsertActiveSuccess(0, "您已配置成功"),
    NOTACTIVE(1,"没有匹配的活动"),
    NUMBER_NOT(1,"用户人数不够");


    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
