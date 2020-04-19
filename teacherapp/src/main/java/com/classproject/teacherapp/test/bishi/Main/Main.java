package com.classproject.teacherapp.test.bishi.Main; ;

import java.util.*;
import java.text.*;

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int calcMinStaff() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        int num = 5;
        return  num;
    }
    static int gg(Map<Integer, Integer> map, int sum){
        int f = -1;
        if(map.isEmpty()){
            return -1;
        }
        for (Integer num:map.values()) {
            if(num > sum){
                if(f == -1){
                    f = sum;
                    continue;
                }
                f = f < sum ? f :sum;
            }

        }
        return  -1;
    }
    /******************************结束写代码******************************/


    public static void main(String[] args) throws ParseException {
      /*  int res;

        //算两个日期间隔多少天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2019-09-25");
        Date date1 = format.parse("2019-10-25");
        int a = (int) ((date1.getTime() - date.getTime()) / (1000*3600*24));*/
        System.out.println(beforeDayByNowDay("yyyy-MM-dd"));

    }
    /**
     * 获取当前日期的前一天
     * @param pattern 需要返回的日期格式，例如：yyyy-MM-dd HH:mm:ss
     * @return 前一天日期字符串
     */
    public static String beforeDayByNowDay(String pattern){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }


    /**
     * 获取两个日期相差几个月
     * @author 石冬冬-Heil Hilter(dd.shi02@zuche.com)
     * @date 2016-11-30 下午7:57:32
     * @param start
     * @param end
     * @return
     */
    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)&& (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }
}
