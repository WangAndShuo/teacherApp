package com.classproject.teacherapp.service;

import com.classproject.teacherapp.entity.AppUserinfo;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/2/19 20:30
 **/
public interface UserInfoService {

    int deleteByPrimaryKey(String uuid);

    int insert(AppUserinfo record);

    int insertSelective(AppUserinfo record);

    AppUserinfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppUserinfo record);

    int updateByPrimaryKey(AppUserinfo record);
}
