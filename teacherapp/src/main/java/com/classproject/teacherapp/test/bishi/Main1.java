package com.classproject.teacherapp.test.bishi;

import com.classproject.teacherapp.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Main1
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/23 19:05
 **/
public class Main1 {
    /**
     * 交易时间
     * @param str
     * @param dateFormat
     * @return
     */
    public static boolean isValidDateTime(String str,String dateFormat) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01

            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }
    public static void main(String[] args) {
        System.out.println(((!DateUtil.checkValidDate("20200202","yyyyMMdd")
                || !DateUtil.checkValidDate("20200101","yyyyMMdd"))));
        System.out.println((!DateUtil.checkValidDate("20200202","yyyy-MM-dd")
                || !DateUtil.checkValidDate("20200101","yyyy-MM-dd")));


                if (!(
                        (DateUtil.checkValidDate("20200202","yyyyMMdd") && DateUtil.checkValidDate("20200101","yyyyMMdd"))
                        || (DateUtil.checkValidDate("20200202","yyyy-MM-dd") && DateUtil.checkValidDate("20200101","yyyy-MM-dd"))
                        )
                ){
            System.out.println("xxxxx");
        }

//            return ResponseMessage.error("日期格式不对");
//        }

//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        String[] arr = str.split(",");
//        int[] narr = new int[arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            narr[i] = Integer.parseInt(arr[i]);
//        }
//        int num = 0;
//        List<Integer> list = get(narr);
//        for ( Integer m : list) {
//            if(m % 3 == 0) {
//                int l = m / 3 ;
//                num = num > l ? num :l ;
//            }
//        }
//        System.out.println(num);
    }

    public static List  get(int[] arr){
        int count = (int)Math.pow(2,arr.length);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int sum =0;
            int tmp = i;
            int index = 0;
            while(tmp != 0){
                if((tmp&1) == 1){
                    //sub.add(arr[index]);
                    sum = sum + arr[index];
                }
                index++;
                tmp = tmp >> 1;
            }
            list.add(sum);
        }
        return list;
    }


    public static void  ge1(int[] arr ,int start ,int end){
        ArrayList<Integer> list = new ArrayList();
        if(start == end){
            for (int i = 0; i <=end ; i++) {
                System.out.print(arr[i]);
            }
            System.out.println();
        }else{
            for (int i = start; i < end ; i++) {
                int t = arr[start];
                arr[start] = arr[i];
                arr[i] = t;

                ge1(arr,start + 1, end);

                t = arr[start];
                arr[start] = arr[i];
                arr[i] = t;
            }
        }
    }
}
