package com.classproject.teacherapp.BigData;

/**
 * @ClassName Lambda
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/13 11:48
 **/

@FunctionalInterface
interface  Foo{
    public void sayHell();
    default int mul(int x,int y){
        return  1;
    }
    default int mul1(int x,int y){
        return  1;
    }
    static int mu1(int x,int y){
        return  1;
    }
    static int mu3(int x,int y){
        return  1;
    }
}

/**
 * 1 - 函数式编程
 *  int  age = 23;
 *
 *  拷贝小括号，写死右箭头，落地大括号
 *  @FunctionalInterface
 *  default方法能写多个
 *  static方法能写多个
 */
public class LambdaExpressDemo02 {

    public static void main(String[] args) {
        /**
         * 接口的调用
         */
        Foo foo = () -> {
            System.out.println("xzsda");
        };
        foo.sayHell();

    }
}
