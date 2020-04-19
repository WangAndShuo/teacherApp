package com.classproject.teacherapp.test.bishi.Main;

import java.util.*;

public class Main1 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static long countDolphin(int n, int m, int[] birthYear, int x) {
        int[] arr = new int[n];
        arr[0] = n;
        //年
        for (int i = 0; i <= x; i++) {
            //猪的岁数
            for (int l = 0; l <= m; l++) {
                //加猪仔和增加年龄
                for (int j = 0; j < birthYear.length; j++) {
                    if(l == birthYear[j] && arr[l] != 0){
                        int tmp = arr[0];
                        arr[0] = tmp+arr[l];
                    }

                }
            }
            if(i != x){
                arr = tmp(arr, m);
            }



        }
        int sum =0;
        for (int i = 0; i <= n ; i++) {
            sum += arr[i];
        }

        return sum;

    }
    /******************************结束写代码******************************/
    static int[] tmp(int[] arr, int m){

        int[]  tmp = new int[m+1];
        tmp[0] = 0;

        for (int i = 1; i <= m; i++) {
            tmp[i] = arr[i-1];
        }
        return tmp;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        long res;

        int _n;
        _n = Integer.parseInt(in.nextLine().trim());

        int _m;
        _m = Integer.parseInt(in.nextLine().trim());

        int _birthYear_size = 0;
        _birthYear_size = Integer.parseInt(in.nextLine().trim());
        int[] _birthYear = new int[_birthYear_size];
        int _birthYear_item;
        for(int _birthYear_i = 0; _birthYear_i < _birthYear_size; _birthYear_i++) {
            _birthYear_item = Integer.parseInt(in.nextLine().trim());
            _birthYear[_birthYear_i] = _birthYear_item;
        }

        int _x;
        _x = Integer.parseInt(in.nextLine().trim());

        res = countDolphin(_n, _m, _birthYear, _x);
        System.out.println(String.valueOf(res));

    }
}
