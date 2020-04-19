package com.classproject.teacherapp.mapper;

import com.classproject.teacherapp.entity.AppClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppClassMapper {

    int deleteByPrimaryKey(String uuid);

    int insert(AppClass record);

    int insertSelective(AppClass record);

    AppClass selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AppClass record);

    int updateByPrimaryKey(AppClass record);

    List<AppClass> listClassByDay(@Param("week") String week);
}