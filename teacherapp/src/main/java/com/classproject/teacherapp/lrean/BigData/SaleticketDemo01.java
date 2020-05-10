package com.classproject.teacherapp.lrean.BigData;

/**
 * @ClassName SaleticketDemo01
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/13 10:57
 **/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：三个售票员    卖出   30张票
 * 笔记：如何编写企业级的多线程代码
 * 固定的编程套路+模板是什么？
 *
 * 1  高内聚低耦合的前提下，线程  操作  资源类
 *  1.1 一言不合，先创建一个资源类
 */

class Ticket //资源类 = 实例变量+实例方法
{
    private int number = 60;
    Lock lock  = new ReentrantLock();

    public  void  sale(){
        lock.lock();
        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "\t 卖出第： "+ (number--) + "\t 还剩下：  " + number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}


public class SaleticketDemo01 {
    //普通运行的时候有两个线程
    //  1 - main线程
    //  2 - GC垃圾回收线程
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread( () -> {
            for (int i = 0; i <= 40 ; i++) {
                ticket.sale();
            }

        },"ThreadAAAAAA").start();

        new Thread( () -> {
            for (int i = 0; i <= 40 ; i++) {
                ticket.sale();
            }

        },"ThreadBBBB").start();

        new Thread( () -> {
            for (int i = 0; i <= 40 ; i++) {
                ticket.sale();
            }

        },"ThreadCCCCCC").start();
    }
}
