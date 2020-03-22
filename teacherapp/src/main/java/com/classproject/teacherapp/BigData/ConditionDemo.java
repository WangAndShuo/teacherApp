package com.classproject.teacherapp.BigData;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionDemo
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/15 12:29
 **/

/**
 * A打印五次  BB打印10次  CC打印15次
 * 三个线程启动，实现A→B→C
 */
class ShareData{
    private volatile int number = 0;
    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public  void get1(){
        lock.lock();
        try{
            while(number != 0){
                condition1.await();
            }
            number++;
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t " + number);
            }

            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void get2(){
        lock.lock();
        try{
            while(number != 1){
                condition2.await();
            }
            number++;
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t " + number);
            }
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void get3(){
        lock.lock();
        try{
            while(number != 2){
                condition3.await();
            }
            number-=2;
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t " + number);
            }
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}


public class ConditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread( () -> {
            for (int i = 0; i < 5; i++) {
                shareData.get1();
            }
        }).start();

        new Thread( () -> {
            for (int i = 0; i < 5; i++) {
                shareData.get2();
            }
        }).start();


        new Thread( () -> {
            for (int i = 0; i < 5; i++) {
                shareData.get3();
            }
        }).start();


    }


}
