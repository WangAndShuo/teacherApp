package com.classproject.teacherapp.mapper;

import com.classproject.teacherapp.entity.AppComJob;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface AppComJobMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppComJob record);

    int insertSelective(AppComJob record);

    AppComJob selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppComJob record);

    int updateByPrimaryKey(AppComJob record);
}