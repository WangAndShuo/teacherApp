package com.classproject.teacherapp.BigData;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @ClassName TestUUID
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/18 13:45
 **/
public class TestUUID {
    public static void main(String[] args) {

        //在不用正则表达式的时候请用 replace 这样会提高性能
        //在使用lambda表达式的时候能使用 ::  这种的不要使用 ->
        //能使用StringBuilder 就不要使用StringBuffer
        double ss = 5.26;
        String s1 = "-" + ss;
        String s2 = "-" + String.valueOf(ss);
        System.out.println(s1.equals(s2));
        double d = 1.1;
        BigDecimal bd1 = BigDecimal.valueOf(d);
        BigDecimal bd2 = new BigDecimal("1.1");
        BigDecimal bd3 = new BigDecimal(1.1);

        BigDecimal bd4 = new BigDecimal(d); // Noncompliant; see comment above

       /* String s = UUID.randomUUID().toString();
        String s1 = s.replace("-", "");
        String s2 = s.replaceAll("-", "");


        System.out.println(s1.equals(s2));*/
    }
}
