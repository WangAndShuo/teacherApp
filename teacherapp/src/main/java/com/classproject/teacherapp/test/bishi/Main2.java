package com.classproject.teacherapp.test.bishi;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Main2
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/23 19:45
 **/
//class Node{
//    String nextval;
//    String  val;
//    Node next;
//    public Node(String val,String nextval){
//        this.val = val;
//        this.nextval = nextval;
//    }
//}

public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        str = str.replace("[[", "");
        str = str.replace("]]", "");
        str = str.replace(",[", "");
        String[] tarr = str.split("]");
        Integer index = Integer.parseInt(tarr[0].split(",")[0]);


        HashMap<Integer, Integer> map = new HashMap<>();
        for (String r : tarr) {
            String[] arr = r.split(",");
            map.put(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        }
        List<Integer> list = new ArrayList<>();
        Integer r = null;
        for (int i = 0; i < map.size(); i++) {
            list.add(index);
            Integer tmp = map.get(index);
            index = map.get(index);
            if (list.get(list.size()-1)>index) {
                r = tmp;
                break;
            }
        }
        int num = 0;
        boolean rl = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(r) || rl) {
                rl = true;
                num++;
            }
        }
        System.out.println(num);

    }

   /* public static boolean get(List<Integer> list, Integer str) {
        boolean r = false;
        for (Integer er :
                list) {
            if (er.equals(str)) {
                r = true;
            }
        }
        return r;
    }*/
}
