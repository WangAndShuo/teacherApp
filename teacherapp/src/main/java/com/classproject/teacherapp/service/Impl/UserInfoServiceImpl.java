package com.classproject.teacherapp.service.Impl;

import com.classproject.teacherapp.mapper.AppUserinfoMapper;
import com.classproject.teacherapp.entity.AppUserinfo;
import com.classproject.teacherapp.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/2/19 20:30
 **/
@EnableScheduling
@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private AppUserinfoMapper appUserinfoMapper;
    //@Scheduled(cron = "0/10 * * * * ?")
    public void stat(){
        Date date = new Date();
        System.out.println(date);
    }

    @Override
    public int deleteByPrimaryKey(String uuid) {
        return 0;
    }

    @Override
    public int insert(AppUserinfo record) {
        return 0;
    }

    @Override
    public int insertSelective(AppUserinfo appUserinfo) {
        return appUserinfoMapper.insertSelective(appUserinfo);
    }

    @Override
    public AppUserinfo selectByPrimaryKey(String uuid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(AppUserinfo record) {
        return appUserinfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AppUserinfo record) {
        return 0;
    }
}
