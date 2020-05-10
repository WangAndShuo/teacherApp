package com.classproject.teacherapp.lrean.BigData;

import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.HashMap;

/**
 * @ClassName HelloGC
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/12 13:48
 **/
public class HelloGC
{
    public static void main(String[] args) throws InterruptedException {
        //返回Java虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        //返回Java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();

        HashMap  hashMap = new HashMap();

        System.out.println("TOTAL_MEMORY(-Xms) = "+ totalMemory + "(字节)" + (totalMemory / (double)1024 / 1024 ) + "MB");
        System.out.println("MAX_MEMORY(-Xmx) = "+ maxMemory + "(字节)" + (totalMemory / (double)1024 / 1024 ) + "MB");

        System.out.println();
        getDiskInfo();

        System.out.println();
        getMemInfo();
//        System.out.println("*********HelloGC");
//        Thread.sleep(Integer.MAX_VALUE);
    }
    public static void getDiskInfo()
    {
        File[] disks = File.listRoots();
        for(File file : disks)
        {
            System.out.print(file.getPath() + "    ");
            System.out.print("空闲未使用 = " + file.getFreeSpace() / 1024 / 1024 + "M" + "    ");// 空闲空间
            System.out.print("已经使用 = " + file.getUsableSpace() / 1024 / 1024 + "M" + "    ");// 可用空间
            System.out.print("总容量 = " + file.getTotalSpace() / 1024 / 1024 + "M" + "    ");// 总空间
            System.out.println();
        }
    }


    public static void getMemInfo()
    {
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println("Total RAM：" + mem.getTotalPhysicalMemorySize() / 1024 / 1024 + "MB");
        System.out.println("Available　RAM：" + mem.getFreePhysicalMemorySize() / 1024 / 1024 + "MB");
    }

}
