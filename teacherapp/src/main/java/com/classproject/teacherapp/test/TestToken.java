package com.classproject.teacherapp.test;

import com.classproject.teacherapp.util.TokenProccessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName TestToken
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/23 14:18
 **/
@Slf4j
public class TestToken {

    @Autowired
    static TokenProccessor println;
    public static void main(String[] args) {
        double fileSize = 10.5;
        int size = 10;
        try{
              int i = 0 / 0 ;
        }catch (Throwable throwable){
             log.info("抛出异常",throwable);
             log.info(" ");
             log.info(" ");
             log.info(" ");
             log.info(" ");

             throwable.printStackTrace();
        }finally {

        }

        System.out.println((fileSize < size));
    }
}
