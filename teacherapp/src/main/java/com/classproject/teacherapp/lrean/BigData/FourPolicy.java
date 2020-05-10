package com.classproject.teacherapp.lrean.BigData;

/**
 * @ClassName Four
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/12 10:15
 **/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * JDK内置 拒绝策略
 */
public class FourPolicy {
    //回退给调用者
    ThreadPoolExecutor callerRunsPolicy = new ThreadPoolExecutor(5, 10, 5L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    //直接抛出异常
    ThreadPoolExecutor abortPolicy = new ThreadPoolExecutor(5, 10, 5L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    //将等待时间最长的线程除去，然后把当前任务加入队列中尝试再次提交当前任务。
    ThreadPoolExecutor discardOldestPolicy = new ThreadPoolExecutor(5, 10, 5L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    //直接丢弃任务：如果等待队列不能在塞入线程，直接弹出要插入的线程
    //不予任何处理也不抛出异常。如果语序任务丢失，这是最好的一种方案。
    ThreadPoolExecutor discardPolicy = new ThreadPoolExecutor(5, 10, 5L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardPolicy());
}
