package com.classproject.teacherapp.dao;

import com.classproject.teacherapp.entity.AppCreJob;

public interface AppCreJobMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppCreJob record);

    int insertSelective(AppCreJob record);

    AppCreJob selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppCreJob record);

    int updateByPrimaryKey(AppCreJob record);
}