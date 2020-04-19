package com.classproject.teacherapp.service.Impl;

import com.classproject.teacherapp.common.Exception.MyTranException;
import com.classproject.teacherapp.mapper.AppUserMapper;
import com.classproject.teacherapp.mapper.AppUserinfoMapper;
import com.classproject.teacherapp.entity.AppUser;
import com.classproject.teacherapp.entity.AppUserinfo;
import com.classproject.teacherapp.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName LoginServiceImpl
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/22 12:09
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AppUserMapper appUserMapper;
    @Resource
    private AppUserinfoMapper appUserinfoMapper;

    @Override
    @Transactional
    public int getUser(AppUser appUser) {
        int num = appUserMapper.selectByUser(appUser);


        return  num;
    }


    @Override
    public int insertUser(AppUser appUser) throws MyTranException {
        int num = appUserMapper.insert(appUser);
        AppUserinfo appUserinfo = new AppUserinfo();
        appUserinfo.setUuid(appUser.getUuid());
        int num1 = appUserinfoMapper.insertSelective(appUserinfo);
        if (num != 1 && num1 != 2) {
            throw new MyTranException("异常");
        }
        return  num;
    }
    @Override
    public int getUserByName(String username){
        int num = appUserMapper.getUserByName(username);
        return num;
    }
}
