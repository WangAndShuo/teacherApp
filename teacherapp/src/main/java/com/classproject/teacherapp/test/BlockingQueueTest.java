package com.classproject.teacherapp.test;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
class  Summer{
    private volatile boolean FLAG  =  true;
    AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public Summer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
        log.info(Thread.currentThread().getName()+"\t");
    }
    public void pro() throws InterruptedException {

        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 1L,TimeUnit.SECONDS);
            if(retValue){
                log.info(Thread.currentThread().getName()+"\t 插入队列"+ data +"成功");
            }else {
                log.info(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        log.info(Thread.currentThread().getName() + "\t 大老板叫停了");

    }

    public void del() throws InterruptedException {

        String retValue = null;
        while (FLAG){
            retValue = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null == retValue || retValue.equalsIgnoreCase("")) {
                FLAG = false;
                log.info(Thread.currentThread().getName() + "\t 超过两秒钟没有取到队列，退出");
                log.info("");
                log.info("");
                log.info("");
                log.info("");
                return;
            }
            log.info(Thread.currentThread().getName() + "\t 消费队列" + retValue + "成功");

        }
        TimeUnit.SECONDS.sleep(1);
    }
    public void stop(){
        this.FLAG = false;
    }

}


@Slf4j
public class BlockingQueueTest {

    BeanFactory beanFactory;

    public static void main(String[] args) {

        BeanFactory beanFactory = new  ClassPathXmlApplicationContext();
        beanFactory.getBean("dsada");

        //BlockingQueue blockingQueue = new SynchronousQueue();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,1000,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        executor.execute(() ->
            log.info("dsadas")
        );
        executor.shutdown();
        Summer summer = new Summer(new ArrayBlockingQueue(10));
        new Thread(() -> {
            log.info("生产线程启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.info("context", e);
                Thread.currentThread().interrupt();
            }
            log.info("");
            log.info("");
            log.info("");
            try {
                summer.pro();
            } catch (Exception e) {
                log.info("error",e);
            }
        },"PRO").start();
        new Thread(() -> {
            log.info("消费线程启动");
            try {
                summer.del();
            } catch (Exception e) {
                log.info("error",e);
            }
        },"CON").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.info("contexts",e);
            Thread.currentThread().interrupt();
        }
        log.info("");
        log.info("");
        log.info("");
        log.info("");

        log.info("五秒钟结束了");

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
