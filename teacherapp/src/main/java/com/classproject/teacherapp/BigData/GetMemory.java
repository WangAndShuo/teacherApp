package com.classproject.teacherapp.BigData;

/**
 * @ClassName GetMemory
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/16 19:21
 **/
public class GetMemory {

    public static void getMaxMemory(){
        long maxMemory = Runtime.getRuntime().maxMemory();//虚拟机试图使用的最大内存量
        System.out.println("-Xmx：Max_Memory = "+maxMemory+"(字节)、"+(maxMemory/(double)1024/1024)+"MB");

    }
    public static void getTotalMemory(){
        long totalMemory = Runtime.getRuntime().totalMemory();//虚拟机中的内存总量
        System.out.println("-Xms：Totaly_Memory = "+totalMemory+"(字节)、"+(totalMemory/(double)1024/1024)+"MB");

    }
}
