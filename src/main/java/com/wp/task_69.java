package com.wp;

/**
 * @author: wp
 * @Title: task_69  69. x 的平方根
 * @Description: https://leetcode-cn.com/problems/sqrtx/
 * @date 2022/2/6 15:30
 */
public class task_69 {
    public static void main( String[] args ) {
        System.out.println( mySqrt( 2147395599 ) );
    }

    public static int mySqrt(int x) {
        if(x<=1){
            return x==1?1:0;
        }
        int min = 1,max = x,middle = 0;
        while (min<=max) {
            middle = min + ((max-min)>>1);
            long res = (long)middle*middle;
            if (res==x) {
                break;
            } else if (res > x) {
                max = middle - 1;
            }else{
                if (((long)middle+1)*(middle+1)>x) {
                    break;
                }
                min = middle + 1;
            }
        }
        return middle;
    }

}
