package com.classproject.teacherapp.BigData;

/**
 *
 * -Xmx14m -Xms14m -XX:+PrintGCDetails -XX:+UseSerialGC -Xss1024k -XX:SurvivorRatio=8  -XX:MetaspaceSize=1024m -XX:NewRatio=4 -XX:+PrintCommandLineFlags
 * @ClassName JVMNote
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/15 21:56
 **/

/*



5 方法区
    5.1 它存储了每一个类的结构信息
    5.2 方法区时规范，在不同虚拟机里头实现是不一样的，最典型的就是永久代（PermGen space）和元空间（Metaspace）

           空调 k1 = new 格力()
           List list = new ArrayList();
方法区 f = new 永久代
方法区 f = new 元空间

6 stack 栈

    6.1 栈管运行，堆管存储
    6.2 栈保存哪些东西？
        8种基本类型的变量+对象的引用变量+实例方法都是在函数的栈内存种分配
    6.3 Exception in thread "main" java.lang.StackOverflowError
        这个算是 错误 不属于异常



8 heap----> 对象生命周期---> OOM
    8.1 老师讲解的heap结构是否正确？
    8.2
 */



public class JVMNote
{
    public static void m(){
        m();
    }


    public static void main(String[] args) {
        int x = 1;
        System.out.println(x < x + 1);
//        GetMemory.getMaxMemory();
//        GetMemory.getTotalMemory();
//        JVMNote jvmNote = new JVMNote();
//        byte[] b = new byte[1*1024*1024];

    }
}
