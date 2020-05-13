package com.classproject.teacherapp.service.Impl;

import com.classproject.teacherapp.entity.AppUser;
import com.classproject.teacherapp.mapper.AppClassMapper;
import com.classproject.teacherapp.entity.AppClass;
import com.classproject.teacherapp.service.ClassService;
import com.classproject.teacherapp.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ClassServiceImpl
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/26 11:01
 **/
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    AppClassMapper appClassMapper;
    @Override
    public Map<String, List<AppClass>> listClassByDay(@RequestParam("weeks") String[] weeks) {
        Map<String, List<AppClass>> map = new HashMap<>();
        for (String str : weeks) {
            List<AppClass> list = appClassMapper.listClassByDay(str);
            map.put(str, list);
        }
        return map;
    }

    @Override
    public BaseResponse getClassByUser(AppUser appUser) {
        List<AppClass> classList = new ArrayList<>();
        classList = appClassMapper.selectByPrimaryKey(appUser.getUuid());
        return BaseResponse.ok("成功", classList);
    }
}
