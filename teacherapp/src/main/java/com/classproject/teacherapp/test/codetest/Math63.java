package com.classproject.teacherapp.test.codetest;

/**
 * @ClassName Math63
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/11 17:21
 **/
public class Math63 {
    public static int movingCount(int threshold, int rows, int cols)
    {
        int all = 0;
        int r = 0;
        for(int i = 0; i <= rows ; i++){
            for(int j = 0; j <= cols ; j++){
                r = getNum(i) + getNum(j);
                if(r <= threshold){
                    all++;
                }else{
                    continue;
                }
            }
        }
        return all;
    }

    public static int getNum(int num){
        int sum=0;
        while(num!=0){
            sum = sum + num%10;
            num = num/10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(15,20,20));

    }
}
