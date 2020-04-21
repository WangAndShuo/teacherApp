package com.classproject.teacherapp.mapper;

import com.classproject.teacherapp.entity.AppUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AppUserMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(String uuid);

    String selectByUser(AppUser appUser);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);

    int getUserByName(String username);
}