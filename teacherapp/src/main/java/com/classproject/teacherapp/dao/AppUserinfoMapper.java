package com.classproject.teacherapp.dao;

import com.classproject.teacherapp.entity.AppUserinfo;

public interface AppUserinfoMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppUserinfo record);

    int insertSelective(AppUserinfo record);

    AppUserinfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppUserinfo record);

    int updateByPrimaryKey(AppUserinfo record);
}