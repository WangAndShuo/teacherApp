package com.classproject.teacherapp.service.Impl;

import com.classproject.teacherapp.dao.AppUserMapper;
import com.classproject.teacherapp.entity.AppUser;
import com.classproject.teacherapp.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginServiceImpl
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/22 12:09
 **/
@Service
public class LoginServiceImpl implements LoginService {

    private AppUserMapper appUserMapper;

    @Override
    public int getUser(AppUser appUser){
        int num = appUserMapper.selectByUser(appUser);
        return  num;
    }
}
