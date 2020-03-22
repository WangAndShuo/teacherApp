package com.classproject.teacherapp.dao;

import com.classproject.teacherapp.entity.AppClass;

public interface AppClassMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppClass record);

    int insertSelective(AppClass record);

    AppClass selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppClass record);

    int updateByPrimaryKey(AppClass record);
}