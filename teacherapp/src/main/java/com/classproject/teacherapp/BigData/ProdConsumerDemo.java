package com.classproject.teacherapp.BigData;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ProdConsumerDemo
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/13 18:21
 **/

class Aircondition{
    private int number = 0;
    private Lock  lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public  void  win(){
        lock.lock();
        try{
            while(number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t " + number);
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public  void  din(){
        lock.lock();
        try{
            while(number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t " + number);
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public synchronized void increment() throws Exception{
        // 1 判断
        while(number != 0){
            this.wait();
        }
        // 2 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t " + number);
        // 3 通知
        this.notifyAll();
    }
    public synchronized void decrement() throws Exception{
        // 1 判断
        while(number == 0){
            this.wait();
        }
        // 2 干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t " + number);
        // 3 通知
        this.notifyAll();

    }

}

/**
 * 现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减一
 * 实现交替，来10轮，变量初始值为零。
 * 1 - 高内聚低耦合，线程操作资源类
 * 2 - 判断/干活/通知
 * 3 - 防止虚假唤醒   if  →  while
 */

public class ProdConsumerDemo {
    public static void main(String[] args) {

        Aircondition aircondition = new Aircondition();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.win();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.din();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread( () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}
