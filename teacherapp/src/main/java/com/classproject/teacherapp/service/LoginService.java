package com.classproject.teacherapp.service;

import com.classproject.teacherapp.common.Exception.MyTranException;
import com.classproject.teacherapp.entity.AppUser;
import com.classproject.teacherapp.util.BaseResponse;

public interface LoginService
{
     BaseResponse getUser(AppUser appUser);

     BaseResponse insertUser(AppUser appUser) throws MyTranException;

     int getUserByName(String username);

}
