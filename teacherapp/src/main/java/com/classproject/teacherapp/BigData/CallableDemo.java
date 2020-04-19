package com.classproject.teacherapp.BigData;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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


class TestThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "This is Thread return value";
    }
}



public class CallableDemo {

//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//
//        //用同一个FutureTask对象只调用一次call方法；
//        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
//        Thread t1 = new Thread(futureTask,"AA");
//        Thread t2 = new Thread(futureTask,"bb");
//        t1.start();
//        t2.start();
//        System.out.println(futureTask.get());
//    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(new TestThread());
        Thread thread = new Thread(future,"aa");
        thread.start();
        System.out.println(future.get());
    }
}
