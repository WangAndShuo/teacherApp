package com.classproject.teacherapp.dao;

import com.classproject.teacherapp.entity.AppComJob;

public interface AppComJobMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppComJob record);

    int insertSelective(AppComJob record);

    AppComJob selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppComJob record);

    int updateByPrimaryKey(AppComJob record);
}