package com.classproject.teacherapp.dao;

import com.classproject.teacherapp.entity.AppWork;

public interface AppWorkMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppWork record);

    int insertSelective(AppWork record);

    AppWork selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppWork record);

    int updateByPrimaryKey(AppWork record);
}