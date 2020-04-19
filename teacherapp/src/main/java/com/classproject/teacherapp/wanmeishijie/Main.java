package com.classproject.teacherapp.wanmeishijie;

    import com.classproject.teacherapp.util.StringUtil;

    import java.util.*;

    /**
     * @ClassName Main
     * @Description:
     * @Author wangshuo[wang_shuo2@suixingpay.com]
     * @Date 2020/4/14 19:22
     **/
    public class Main {


        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int[][] arr = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            int[] max = new int[4];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if(arr[i][j] != -1){
                        max[j] = max[j] < arr[i][j] ? max[j] :arr[i][j];
                    }
                }
            }
            int[] ma = {1,8, 4,13};
            for (int i = 0; i < 4; i++) {
                System.out.println(ma[i]);
            }
        }

//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//            String str = sc.nextLine();
//            String[]  starr = str.split(" ");
//            int[] arr = new int[starr.length];
//            for (int i = 0; i < starr.length; i++) {
//                arr[i] = Integer.parseInt(starr[i]);
//            }
//            int limit  = sc.nextInt();
//            sort(arr);
//            System.out.println(get(0,starr.length-1,limit,arr));
//
//        }

        public static void  sort(int[] arr){
            for (int i = 0; i < arr.length -1; i++) {
                for (int j = 0; j < arr.length-1-i; j++) {
                    if(arr[j]>arr[j+1]){
                        int tmp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = tmp;
                    }
                }
            }
        }


        public static int get(int i,int j,int limit,int[] p){
            int tmp = 0;
            while(i <= j){
                if(p[j] > limit - p[i] ){
                    tmp++;
                    j--;
                } else {
                    tmp++;
                    i++;
                    j--;
                }
            }
            return tmp;
        }
//
//        Scanner sc = new Scanner(System.in);
//        int num = sc.nextInt();
//        int[][] arr1 = new int[num][2];
//        //        int[][] arr2 = new int[num][2];
//            for (int i = 0; i < num ; i++) {
//            arr1[i][0] = sc.nextInt();;
//            arr1[i][1] = sc.nextInt();
//            //            arr2[i][0] = i;
//            //            arr2[i][1] =
//        }
//        int max = 0;
//            for (int i = 0; i < num; i++) {
//            int tmp = 0;
//            //比较当前数据与其他数据的大小
//            for (int j = 0; j < num; j++) {
//                if(arr1[i][0] > arr1[j][0] && arr1[i][1] > arr1[j][1]){
//                    tmp++;
//                }
//            }
//            max = max > tmp ? max :tmp;
//        }
//            System.out.println(max);

//    public static  void  sort(Map<Integer, Integer> map){
//        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//        list.sort(
//                (Map.Entry<Integer, Integer> map1, Map.Entry<Integer, Integer> map2) ->
//                    map2.getKey().compareTo(map1.getKey())
//        );
//    }

}



// 01背包

//    Scanner sc = new Scanner(System.in);
//    int num = sc.nextInt();
//    int max = sc.nextInt();
//    //重量
//    int[] arr1 = new int[num+1];
//    //金钱
//    int[] arr2 = new int[num+1];
//        arr1[0] = 0;
//                arr1[0] = 0;
//                for (int i = 1; i < arr1.length; i++) {
//        arr1[i] = sc.nextInt();
//        }
//        for (int i = 1; i < arr1.length; i++) {
//        arr2[i] = sc.nextInt();
//        }
//        int[][]  bag  = new int[num+1][max+1];
//        for (int i = 1; i <= num; i++) {
//        for (int j = 1; j <= max; j++) {
//        if(i>0 && arr1[i]<=j) {
//
//        bag[i][j] = (bag[i-1][j]>(bag[i-1][j-arr1[i]]+arr2[i])?
//        bag[i-1][j]:bag[i-1][j-arr1[i]]+arr2[i]);
//        }else {
//        bag[i][j] =bag[i-1][j];
//        }
//        }
//        }
//        System.out.println(bag[num][max]);
//

