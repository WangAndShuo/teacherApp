package com.classproject.teacherapp.service;

import com.classproject.teacherapp.entity.AppClass;
import com.classproject.teacherapp.entity.AppUser;
import com.classproject.teacherapp.util.BaseResponse;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ClassService
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/26 10:59
 **/
public interface ClassService {
    Map<String, List<AppClass>> listClassByDay(String[] weeks);
    BaseResponse getClassByUser(AppUser appUser);

}
