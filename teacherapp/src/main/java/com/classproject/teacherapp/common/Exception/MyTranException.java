package com.classproject.teacherapp.common.Exception;

/**
 * @ClassName MyTranException
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/26 11:42
 **/
public class MyTranException extends Exception{
    /*无参构造函数*/
    public MyTranException(){
        super();
    }
    //用详细信息指定一个异常
    public MyTranException(String message){
        super(message);
    }

    //用指定的详细信息和原因构造一个新的异常
    public MyTranException(String message, Throwable cause){
        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public MyTranException(Throwable cause) {
        super(cause);
    }
}
