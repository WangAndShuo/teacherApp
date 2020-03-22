package com.classproject.teacherapp.BigData;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName NotSafeDemo03
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/13 14:44
 **/

/**
 *  1 - 故障现象
 *      java.util.ConcurrentModificationException
 *  2 - 导致原因
 *      并发争抢修改导致，参考我们的花名册签名情况
 *  3 - 解决方法
 *      3.1 用 List list = new Vector();
 *      3.2 用 Collections.synchronizedList(new ArrayList<>());
 *      3.3 用  new  CopyOnWriteArrayList();
 */
public class NotSafeDemo03 {
    //扩容是原值的一半
    //当是15时下次扩容是22   取整


    public static void main(String[] args) {
        /**
           什么是写时复制（CopyOnWrite）
               CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器object[]进行Copy
              复制出一个新的容器Object[] newElements，然后新的容器object[] newElements里添加元素，添加完元素之后，
               再将元容器的引用指向新的容器 setArray(newElements);这样做的好处是可以对CopyOnWrite容器进行并发的读而不需要加锁，
              因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
               public boolean add(E e) {
                  final ReentrantLock lock = this.lock;
                  lock.lock();
                  try {
                      Object[] elements = getArray();
                      int len = elements.length;
                      Object[] newElements = Arrays.copyOf(elements, len + 1);
                      newElements[len] = e;
                      setArray(newElements);
                      return true;
                  } finally {
                      lock.unlock();
                  }
              }
         */
        List arrayList = new  CopyOnWriteArrayList();
        List List = Collections.synchronizedList(new ArrayList<>());
        List list = new Vector();


        HashSet hashSet = new HashSet();

        Set arrayList1 = new CopyOnWriteArraySet();
        for (int i = 0; i < 10; i++) {
            new Thread( () -> {
                arrayList.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(arrayList);
            },String.valueOf(i)).start();
        }


//        new Thread( () -> {
//            for (int i = 0; i < 10; i++) {
//                arrayList.remove(i);
//            }
//        }).start();
    }
}
