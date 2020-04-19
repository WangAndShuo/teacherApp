package com.classproject.teacherapp.service;

import com.classproject.teacherapp.common.Exception.MyTranException;
import com.classproject.teacherapp.entity.AppUser;

public interface LoginService
{
     int getUser(AppUser appUser);
     int insertUser(AppUser appUser) throws MyTranException;
     int getUserByName(String username);

}
