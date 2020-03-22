package com.classproject.teacherapp.BigData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @ClassName Teat
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/19 19:04
 **/
public class Teat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int  n = sc.nextInt();
        ArrayList<Integer> arr1 = new ArrayList();
        ArrayList<Integer> arr2 = new ArrayList();
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < n; i++) {
            arr1.add(sc.nextInt());
        }
        for (int i = 0; i < n; i++) {
            arr2.add(sc.nextInt());
        }
        if(n == 1){
            System.out.println( arr1.get(0));
            return;
        }else if(n == 2){
            System.out.println((arr1.get(0) + arr1.get(1)) > (arr2.get(0) + arr2.get(1)) ? (arr1.get(0) + arr1.get(1)) :  (arr2.get(0) + arr2.get(1)));
            return;
        }
        arr1.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return o1 > o2 ? 0 : 1;
            }
        });
        arr2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return o1 > o2 ? 0 : 1;
            }
        });


        if (n>=3) {
             sum1 = arr1.get(n-1) + arr1.get(n-2) + arr1.get(n-3);
             sum2 = arr2.get(n-1) + arr2.get(n-2) + arr2.get(n-3);
        }

        System.out.println(sum1 > sum2 ? sum1 : sum2);


    }
}
