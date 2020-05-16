package com.classproject.teacherapp.mapper;

import com.classproject.teacherapp.entity.AppNote;

import java.util.List;

public interface AppNoteDao {
    int deleteByPrimaryKey(String noteId);

    int insert(AppNote record);

    int insertSelective(AppNote record);

    AppNote selectByPrimaryKey(String noteId);

    List<AppNote> selectByUser(String userId);

    int updateByPrimaryKeySelective(AppNote record);

    int updateByPrimaryKey(AppNote record);
}