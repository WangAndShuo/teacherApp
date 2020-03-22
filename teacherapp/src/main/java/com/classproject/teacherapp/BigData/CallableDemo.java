package com.classproject.teacherapp.BigData;

import io.swagger.models.auth.In;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableDemo
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/11 19:25
 **/
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //用同一个FutureTask对象只调用一次call方法；
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread t1 = new Thread(futureTask,"AA");
        Thread t2 = new Thread(futureTask,"bb");
        t1.start();
        t2.start();

        System.out.println(futureTask.get());

    }
}
