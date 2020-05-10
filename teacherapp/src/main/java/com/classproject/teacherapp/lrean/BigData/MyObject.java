package com.classproject.teacherapp.lrean.BigData;

/**
 * @ClassName
 *
 *
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 *   @Date 2020/3/15 14:19
 **/
public class MyObject {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println("Object的类加载器" + o.getClass().getClassLoader());

        MyObject myObject = new MyObject();
        System.out.println("MyObject爷爷辈的加载器:" + myObject.getClass().getClassLoader().getParent().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader());

        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
