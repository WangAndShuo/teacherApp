package com.classproject.teacherapp.test;

import lombok.Data;
import org.joda.time.DateTime;

/**
 * @ClassName Son
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/5 18:16
 **/
@Data
public class Son extends  Father {

    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println("原格式："+dateTime);
        System.out.println("格式化："+dateTime.toDate());
    }
}
