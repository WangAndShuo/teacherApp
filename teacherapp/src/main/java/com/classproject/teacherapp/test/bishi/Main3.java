package com.classproject.teacherapp.test.bishi;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * @ClassName Main3
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/23 20:42
 **/
public class Main3 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        str = str.replace("[", "");
        str = str.replace("]", "");
        String[] tarr = str.split(",");
        int[] arr = new int[tarr.length];
        for (int i = 0; i < tarr.length-1; i++) {

            int r = 0;
            int l = 0;
            if(i == 0){
                arr[i] = Integer.parseInt(tarr[i]);
                i+=1;
            }

            if((i % 2) ==0){
                r = Integer.parseInt(tarr[i])+30;
            }else{
                l = Integer.parseInt(tarr[i])-30;
            }
            int fri = Integer.parseInt(tarr[i-1]);
            int next = Integer.parseInt(tarr[i+1]);


            if(i != 0){
                if((i % 2) == 1 && (l-30) > fri){
                    arr[i] = l;
                    continue;
                }else if((i % 2) ==0  && (r+30) > next){
                    arr[i] = l;;

                }
            }
        }
        arr[tarr.length-1] = Integer.parseInt(tarr[tarr.length-1])+30;
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
    public void addTime(String s1, String s2){
        LocalTime start = LocalTime.of(0,0,0);
        LocalTime time1 = LocalTime.parse(s1);
        LocalTime time2 = LocalTime.parse(s2);
        Duration duration = Duration.between(start,time2);
        LocalTime time = time1.plus(duration);
        System.out.println(time);
    }
}
