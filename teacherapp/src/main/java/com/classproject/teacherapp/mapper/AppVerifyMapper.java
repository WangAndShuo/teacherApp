package com.classproject.teacherapp.mapper;

import com.classproject.teacherapp.entity.AppVerify;

public interface AppVerifyMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppVerify record);

    int insertSelective(AppVerify record);

    AppVerify selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppVerify record);

    int updateByPrimaryKey(AppVerify record);
}