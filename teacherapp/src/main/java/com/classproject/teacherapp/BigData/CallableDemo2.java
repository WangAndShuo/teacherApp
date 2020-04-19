package com.classproject.teacherapp.BigData;

import java.util.concurrent.*;

/**
 * @ClassName CallableDemo2
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/11 19:40
 **/


// Runnable 没有返回值Callable<V> 有返回值
// Callable<V> 会抛异常  Runnable 不会抛异常
// 接口实现得方法不一样
class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return 105;
    }
}


public class CallableDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread2());
        Thread thread =  new Thread(futureTask,"AA");
        thread.start();
        System.out.println(thread.getName() + ":  " + futureTask.get());
    }
}
