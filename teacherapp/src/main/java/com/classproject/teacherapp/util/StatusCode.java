package com.classproject.teacherapp.util;


public enum StatusCode {


    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    INVALIDPARAMS(400, "非法的参数!"),
    USERNOTLOGIN(401, "用户没登录"),
//    UserPoolIsNull(20000, "抱歉，今日用户数已被抢完，请您留意下次活动"),
//    Activity_Ended(20001, "抱歉，本场活动已结束"),
//    HAVE_JOINED(20003, "您已经参加过本场活动了!"),
//    InsertActiveSuccess(0, "您已配置成功"),
//    NOTACTIVE(1,"没有匹配的活动"),
//    NUMBER_NOT(1,"用户人数不够"),
    SYSTEM_ERROR(20002, "系统开小差了"),
    USER_IS_HAVE(1, "用户已存在"),
    TRUE(0, "true"),

    ;


    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }




}
