package com.wp;

/**
 * @author: wp
 * @Title: BSearch
 * @Description: 二分法求一个数x的平方根y？
 * @date 2022/2/6 14:53
 */
public class BSearch {

    public static void main( String[] args ) {
        double x = 4;
        System.out.println( solution( 4 ) );
    }

    public static double solution(double x){
        if (x==1) {
            return 1;
        }
        double min,max;
        if (x<1) {
            min = x;
            max = 1;
        }else{
            min = 1;
            max = x;
        }
        double res = -1;
        while (min<=max) {
            double middle = min + (max - min)/2;
            double tmp = (middle+0.000001)*(middle+0.000001);
            if (tmp==x) {
                res =  middle+0.000001;
                break;
            } else if (tmp>x) {
                max = middle;
            }else{
                min = middle;
            }
        }
        return res;
    }
}
