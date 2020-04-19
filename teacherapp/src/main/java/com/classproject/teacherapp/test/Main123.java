package com.classproject.teacherapp.test;


import java.time.Duration;
import java.time.LocalTime;
import java.util.*;


/**
 * @ClassName Main
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/22 20:43
 **/
public class Main123 {

    Scanner sc = new Scanner(System.in);

    /**
     * 给你一个时间时间 "10:00:00"
     * 加上过去的时间 "00:11:22"
     * 所得结果 "10:11:22"
     */
    public void addTime(){
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        LocalTime start = LocalTime.of(0,0,0);
        LocalTime time1 = LocalTime.parse(s1);
        LocalTime time2 = LocalTime.parse(s2);
        Duration duration = Duration.between(start,time2);
        LocalTime time = time1.plus(duration);
        System.out.println(time);
    }

    /**
     * 给定两个字符串，第二个字符串比第一个短
     * 判断第二个字符串和它的逆序字符串，是否在第一个字符串当中
     * 如果是输出 TRUE  否 输出 FALSE
     */
    private  void strNum() {
        String str = sc.nextLine();
        String tr = sc.nextLine();
        StringBuffer buffer = new StringBuffer(tr);
        String tr1 = buffer.reverse().toString();
        String r = "FLASE";
        for (int i = 0; i < str.length(); i++) {
           for (int j = i; j <= str.length(); j++){
               if(str.substring(i,j).equals(tr) || str.substring(i,j).equals(tr1)){
                   r = "TRUE";
                   System.out.println(r);
                   return;
               }
            }
        }
        System.out.println(r);
    }

    /**
     * 给定一个字符串输出有多少对括号的个数和所对应的下标
     */
    private  void printIndex() {
        String str = sc.nextLine();
        if(str.equals("")){
            System.out.println(0);
            System.out.println(0);
            System.out.println(0);
            return;
        }
        int l = 0;
        Stack<Integer> stack = new Stack();
        HashMap<Integer,Integer> map = new HashMap();
        for (int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '('){
                    l++;
                    stack.push(i);
                }else if(str.charAt(i) == ')'){
                    int n = stack.peek();
                    map.put(n,i);
                    stack.pop();
                }
        }
        System.out.println(l);
        for (Integer k: map.keySet()) {
            System.out.println(k);
            System.out.println(map.get(k));
        }
    }
}
