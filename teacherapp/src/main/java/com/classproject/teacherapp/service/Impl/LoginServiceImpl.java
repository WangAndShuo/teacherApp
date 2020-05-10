package com.classproject.teacherapp.service.Impl;

import com.classproject.teacherapp.common.Exception.MyTranException;
import com.classproject.teacherapp.mapper.AppUserMapper;
import com.classproject.teacherapp.mapper.AppUserinfoMapper;
import com.classproject.teacherapp.entity.AppUser;
import com.classproject.teacherapp.entity.AppUserinfo;
import com.classproject.teacherapp.mapper.User;
import com.classproject.teacherapp.service.LoginService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Autowired
//    private RedisUtils redisUtils;

    @Override
    @Transactional
    public BaseResponse getUser(AppUser appUser) {
        String uuid = appUserMapper.selectByUser(appUser);
        if(StringUtils.isBlank(uuid)){
            return BaseResponse.error("用户名或密码不正确");
        }
        //查看redis中是否有用户
//        if(null == (User)redisUtils.get(appUser.getUsername())){
//            redisUtils.set(appUser.getUsername(),true);
//        }
        AppUserinfo userinfo = appUserinfoMapper.selectByPrimaryKey(uuid) ;
        return  BaseResponse.build(StatusCode.SUCCESS,userinfo);
    }


    @Override
    public BaseResponse insertUser(AppUser appUser) throws MyTranException {
        int num = appUserMapper.insert(appUser);
        AppUserinfo appUserinfo = new AppUserinfo();
        appUserinfo.setUuid(appUser.getUuid());
        int num1 = appUserinfoMapper.insertSelective(appUserinfo);
        if (num != 1 && num1 != 2) {
            throw new MyTranException("插入失败");
        }
        return  BaseResponse.ok("注册成功");
    }
    @Override
    public int getUserByName(String username){
        int num = appUserMapper.getUserByName(username);
        return num;
    }
}
