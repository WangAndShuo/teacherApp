package com.classproject.teacherapp.BigData;


import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*******发短型");
    }

    public  synchronized void sendSMS() throws Exception{
        System.out.println("*******发SMS");
    }
    public void sayHello(){
        System.out.println("*******+++++");
    }
}

/**
 * @ClassName Lcok8Demo05
 * @Date 2020/3/13 16:04
 *
 *  1 - 标准访问，请问先打印邮件还是短信
 *  2 - 暂停4秒钟在邮件方法，请问先打印邮件还是短信
 *  3 - 新增普通sayHello方法，请问是先打印邮件还是hello
 *  4 - 两部手机，请问先打印邮件还是短信
 *  5 - 两个静态同步方法，同一部手机，请问先打印邮件还是短信
 *  6 - 两个静态同步方法，2 部手机，请问先打印邮件还是短信
 *  7 - 一个静态同步方法，一个普通同步方法，同一部手机，请问先打印邮件还是短信
 *  8 - 一个静态同步方法，一个普通同步方法，2 部手机，请问先打印邮件还是短信
 *
 **/

public class Lcok8Demo05 {
    public static void main(String[] args) throws InterruptedException{
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread( () -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        TimeUnit.MILLISECONDS.sleep(100);
        new Thread( () -> {
            try {
                //phone.sendSMS();
                phone.sayHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();



     }
}
