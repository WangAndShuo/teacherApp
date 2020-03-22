package com.classproject.teacherapp.BigData;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @ClassName HashMap
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/12 16:51
 **/
public class HashMapDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        HashMap hashMap = new HashMap();
        System.out.println(hashMap.size());
        Class clazz = HashMap.class;
// threshold是hashmap对象里的一个私有变量，若hashmap的size超过该数值，则扩容。这是通过反射获取该值
        Field field = clazz.getDeclaredField("threshold");
//setAccessible设置为true可以开启对似有变量的访问
        field.setAccessible(true);
        int threshold = 0;
        for (int i = 0; i < 100; i++) {
            hashMap.put(String.valueOf(i), 0);
            if ((int) field.get(hashMap) != threshold) {
                threshold = (int) field.get(hashMap);
// 默认的负载因子是0.75,也就是说实际容量是/0.75
                System.out.println((int) field.get(hashMap) / 0.75);
            }
        }
    }
}
