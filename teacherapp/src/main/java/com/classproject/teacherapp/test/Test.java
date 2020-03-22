package com.classproject.teacherapp.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName Test
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/5 18:16
 **/
public class Test {
    public static void main(String[] args) {
        Father father = new Father();
        Son son = new Son();
        father.setA(1);
        father.setB(2);
        fatherToChild(father,son);
        System.out.println(son.getA());
    }
    private static void fatherToChild(Object father, Object child) {
        if (!(child.getClass().getSuperclass() == father.getClass())) {
            try {
                throw new Exception(child + "不是" + father + "的子类");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Class fatherClass = father.getClass();
        Field[] fatherClassDeclaredFields = fatherClass.getDeclaredFields();
        for (Field tmpField : fatherClassDeclaredFields) {
            tmpField.setAccessible(true);
            Method method = null;
            try {
                method = fatherClass.getMethod("get" + upperHeadChar(tmpField.getName()));
                Object invoke = method.invoke(father);
                tmpField.set(child, invoke);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    private static  String upperHeadChar(String name) {
        String head = name.substring(0, 1);
        return head.toUpperCase() + name.substring(1, name.length());
    }

//    public static <T>void fatherToChild(T father,T child) throws Exception {
//        if (child.getClass().getSuperclass()!=father.getClass()){
//            throw new Exception("child 不是 father 的子类");
//        }
//        Class<?> fatherClass = father.getClass();
//        Field[] declaredFields = fatherClass.getDeclaredFields();
//        for (int i = 0; i < declaredFields.length; i++) {
//            Field field=declaredFields[i];
//            Method method=fatherClass.getDeclaredMethod("get"+upperHeadChar(field.getName()));
//            Object obj = method.invoke(father);
//            field.setAccessible(true);
//            field.set(child,obj);
//        }
//
//    }
//
//    /**
//     * 首字母大写，in:deleteDate，out:DeleteDate
//     */
//    public static String upperHeadChar(String in) {
//        String head = in.substring(0, 1);
//        String out = head.toUpperCase() + in.substring(1, in.length());
//        return out;
//    }

}
