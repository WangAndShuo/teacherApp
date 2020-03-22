package com.classproject.teacherapp.BigData;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int  n = sc.nextInt();
        int[] arr = new int[n];
        int l = 1;
        int now = -1;
        int all = 1;
        int rl = 1;

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
//        if (n == 1) {
//            System.out.println(arr[0]);
//            return;
//        }

        for (int i = 0; i < n-1; i++) {
            if(now == -1) {
                now = i;
            }
            while(now != -1 && now +1 < n){
                if(arr[now] >= arr[now+1]){
                    if(rl == 1){
                        rl = -1;
                        if(now+2 < n  && arr[now] < arr[now+2] ){
                            l++;
                            now = now + 2;
                            all = all < l ? l : all;
                        }
                    } else{
                        now = -1;
                        l = 1;
                        rl = 1;
                    }
                }
                else{

                    if(arr[now] < arr[now+1]){
                        l++;
                        now++;
                        all = all < l ? l : all;
                    }

                }
            }
            now = i;
            if(now + 2 < n && arr[now] < arr[now+2]){
                rl = -1;
                l = 2;
                now = now + 2;
                all = all < l ? l : all;

                while(now != -1 && now +1 < n){
                    if(arr[now] >= arr[now+1]){
                        if(rl == -1){
                            now = -1;
                            l = 1;
                            rl = 1;
                        }
                    }
                    else{

                        if(arr[now] < arr[now+1]){
                            l++;
                            now++;
                            all = all < l ? l : all;
                        }

                    }
                }

            }

        }
        System.out.println(all);

    }
}
