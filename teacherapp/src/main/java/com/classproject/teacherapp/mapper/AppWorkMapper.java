package com.classproject.teacherapp.mapper;

import com.classproject.teacherapp.entity.AppWork;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppWorkMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppWork record);

    int insertSelective(AppWork record);

    AppWork selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppWork record);

    int updateByPrimaryKey(AppWork record);
}