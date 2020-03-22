package com.classproject.teacherapp.BigData;

import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.concurrent.*;

/**
 * @ClassName MyThreadPoolDemo
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/11 20:50
 **/
public class MyThreadPoolDemo {
    public volatile int a = 0;
    public static void main(String[] args) throws InterruptedException {
        // Array  Arrays
        // Collection Conllections
        // Executor Executors



        Hashtable hashtable = new Hashtable();
        ExecutorService executorService  = new ThreadPoolExecutor(
                2,
                4,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 9; i++) {
            executorService.execute( () -> {
                System.out.println(Thread.currentThread().getName() + "\t 办理业务");
            });
        }
        executorService.shutdown();
        System.out.println(Runtime.getRuntime().availableProcessors());


    }

    private static void threadPoolInit() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                10,5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
        Thread thread = new Thread();
        for (int i = 0; i < 50000000; i++) {
            executor.execute( () -> {
                System.out.println(Thread.currentThread().getName()+"\t  执行完毕");
            });
        }
        executor.shutdown();
    }
}
