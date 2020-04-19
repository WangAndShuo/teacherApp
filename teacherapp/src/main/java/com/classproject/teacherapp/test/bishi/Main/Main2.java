package com.classproject.teacherapp.test.bishi.Main;

import java.util.Scanner;

/**
 * @ClassName Main2
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/1 20:46
 **/
public class Main2 {

    static String[]  arr = { "surprise", "happy", "ctrip", "travel", "wellcome","student","system","program","editor"};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        for (int i = 0; i < arr.length; i++) {
            int num = 0;
            for (int j = 0; j < arr[i].length(); j++) {
                for (int k = 0; k < str.length(); k++) {
                    if(str.charAt(k) == arr[i].charAt(j)){
                        num++;
                        break;
                    }
                }
            }
            if(num + 2 >= arr[i].length() ){
                System.out.println(arr[i]);
            }
        }

    }


}
