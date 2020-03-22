package com.classproject.teacherapp.dao;

import com.classproject.teacherapp.entity.AppUser;

public interface AppUserMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(String uuid);

    Integer selectByUser(AppUser appUser);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);
}