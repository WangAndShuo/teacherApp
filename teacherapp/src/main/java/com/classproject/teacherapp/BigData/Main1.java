package com.classproject.teacherapp.BigData;

        import java.util.Scanner;

/**
 * @ClassName Main1
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/19 20:13
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int  n = sc.nextInt();
        int  k = sc.nextInt();
        int  m = sc.nextInt();
        int  p = sc.nextInt();
        int  q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < k; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(3);
    }


    public static void tt(){
        Scanner sc = new Scanner(System.in);
        int  n = sc.nextInt();
        int  m = sc.nextInt();
        int  s = sc.nextInt();

        int[][] arr = new int[m][3];
        for (int i = 0; i < m; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        int  k = sc.nextInt();
        System.out.println(2);
    }

}
