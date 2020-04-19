package com.classproject.teacherapp.util;

import java.util.Random;

/**
 * @ClassName CodeSender
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/25 21:07
 **/
public class CodeSender {


    public static String getCode(){
        StringBuilder stb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stb.append(random.nextInt(9));
        }
        return  stb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getCode());
    }
}
