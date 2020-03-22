package com.classproject.teacherapp.test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName SpinLockDemo
 * @Description: TODO: 自旋锁学习练习
 *
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/2/24 9:46
 **/


public class SpinLockDemo  {
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    volatile int a =9;


    public  void   mylock(){
        Thread thread  =  Thread.currentThread();
        while(!threadAtomicReference.compareAndSet(null,thread)){

        }
    }

    public void unlock(){
        Lock lock = new ReentrantLock();
        Thread thread = Thread.currentThread();
        while(threadAtomicReference.compareAndSet(thread,null)){

        }
    }


    public static void main(String[] args) {
//        SpinLockDemo demo = new SpinLockDemo();
//        new Thread(() -> {
//           try {
//               TimeUnit.SECONDS.sleep(5);
//           }catch (Exception e){
//           }
//
//        },"AA").start();
        Hashtable map = new Hashtable();
        Set set = new HashSet();
        map.put("",1312);
        map.put("",163156);
        ReentrantReadWriteLock wrlock = new ReentrantReadWriteLock();
        map.clear();

        AtomicInteger atomicInteger = null;
        atomicInteger.incrementAndGet();
        CountDownLatch count = new CountDownLatch(1);

        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();

        System.out.println(map.size());
//        try {
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        wrlock.readLock().lock();
//        for(int i = 1; i <= 5; i++){
//                new Thread(() -> {
//                    System.out.println();
//                },String.valueOf(i)).start();
//        }


    }

}
