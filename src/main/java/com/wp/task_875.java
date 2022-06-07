package com.wp;

/**
 * @author: wp
 * @Title: task_875  875. 爱吃香蕉的珂珂
 * @Description: https://leetcode.cn/problems/koko-eating-bananas/
 * @date 2022/6/7 17:35
 */
public class task_875 {
    public static void main( String[] args ) {
        int[] piles = {3,6,7,11};
        System.out.println( minEatingSpeed( piles, 8 ) );
    }
    public static int minEatingSpeed(int[] piles, int h) {
        int min = 1,max = 0;
        for (int i = 0; i < piles.length; i++) {
            if (max<piles[i]) {
                max = piles[i];
            }
        }
        while (min<=max) {
            int middle = (max-min)/2 + min;
            int num = getNum( piles, middle );
            if (num>h) {
                min = middle+1;
            }else if(num<h){
                max = middle-1;
            }else{
                min = middle;
                break;
            }
        }
        for (int i = min-1; i > 0; i--) {
            int num = getNum( piles, i );
            if (num<=h) {
                min  = i;
            }else{
                break;
            }
        }
        return min;
    }

    private static int getNum( int[] piles, int size ) {
        int num = 0;
        for (int pile : piles) {
            num += pile%size!=0?(pile/size)+1:pile/size;
        }
        return num;
    }
}