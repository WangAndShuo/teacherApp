package com.classproject.teacherapp.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ProdConsumerDemo
 * @Description: TODO: 利用LOCK 实现线程通信；  加一； 减一；
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/5 13:57
 **/

class ShareFata  //资源类
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void incnrement(){
        lock.lock();
        try{
            while(number != 0 ){
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decnrement(){
        lock.lock();
        try{
            while(number == 0 ){
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}



public class ProdConsumerDemo {
    public static void main(String[] args) {
        ShareFata shareFata = new ShareFata();
        new Thread( () -> {
            for (int i = 0; i < 5; i++) {
                shareFata.incnrement();
            }
        }).start();

        new Thread( () -> {
            for (int i = 0; i < 5; i++) {
                shareFata.decnrement();
            }
        }).start();
    }
}
