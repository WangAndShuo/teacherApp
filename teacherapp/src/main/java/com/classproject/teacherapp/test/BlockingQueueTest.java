package com.classproject.teacherapp.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName BlockingQueueTest
 * @Description: TODO: 消息队列版的生产者和消费者模式；
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/4 16:38
 **/

class  Summer{
    private volatile boolean FLAG  =  true;
    AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public Summer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(Thread.currentThread().getName()+"\t");
    }
    public void pro() throws  Exception{

        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 1L,TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+ data +"成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停了");

    }

    public void del() throws  Exception{

        String retValue = null;
        while (FLAG){
            retValue = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null == retValue || retValue.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过两秒钟没有取到队列，退出");
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + retValue + "成功");

        }
        TimeUnit.SECONDS.sleep(1);
    }
    public void stop(){
        this.FLAG = false;
    }

}



public class BlockingQueueTest {

    BeanFactory beanFactory;

    public static void main(String[] args) {

        BeanFactory beanFactory = new  ClassPathXmlApplicationContext();
        beanFactory.getBean("dsada");

        BlockingQueue blockingQueue = new SynchronousQueue();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,1000,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        executor.execute(() ->{
            System.out.println("dsadas");
        });
        executor.shutdown();
        Summer summer = new Summer(new ArrayBlockingQueue(10));
        new Thread(() -> {
            System.out.println("生产线程启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println();
            System.out.println();
            try {
                summer.pro();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"PRO").start();
        new Thread(() -> {
            System.out.println("消费线程启动");
            try {
                summer.del();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"CON").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("五秒钟结束了");

        summer.stop();

//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("a1"));
//        System.out.println(blockingQueue.add("a2"));
//        System.out.println(blockingQueue.add("a3"));
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.offer("s"));
//        System.out.println(blockingQueue.poll());
//        new Thread( () -> {
//            try {
//                System.out.println(Thread.currentThread().getName()+"\t put 1");
//                blockingQueue.put("1");
//                System.out.println(Thread.currentThread().getName()+"\t put 2");
//                blockingQueue.put("2");
//                System.out.println(Thread.currentThread().getName()+"\t put 3");
//                blockingQueue.put("3");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"Thraed  A:").start();
//
//
//
//        new Thread(() -> {
//            try {
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"Thread B:").start();
    }

}
