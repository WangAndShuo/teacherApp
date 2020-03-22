package com.classproject.teacherapp.entity;

import lombok.Data;
import lombok.NonNull;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-03-22
 */
@Data
public class AppUser {
    /**
     * uuid
     */
    @NonNull
    private String uuid;

    /**
     * 用户名
     */
    @NonNull
    private String username;

    /**
     * 密码
     */
    @NonNull
    private String password;


}