package com.classproject.teacherapp.mapper;

import com.classproject.teacherapp.entity.AppUserinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppUserinfoMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppUserinfo record);

    int insertSelective(AppUserinfo record);

    AppUserinfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppUserinfo record);

    int updateByPrimaryKey(AppUserinfo record);

    List<AppUserinfo> selectByClass(String classId);
}