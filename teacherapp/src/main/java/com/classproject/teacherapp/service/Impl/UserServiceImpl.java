package com.classproject.teacherapp.service.Impl;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/2/19 20:30
 **/
@EnableScheduling
@Service
public class UserServiceImpl {

    //@Scheduled(cron = "0/10 * * * * ?")
    public void stat(){
        Date date = new Date();
        System.out.println(date);
}
}
