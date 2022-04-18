package com.wp;

/**
 * @author: wp
 * @Title: task_479  479. 最大回文数乘积
 * @Description: https://leetcode-cn.com/problems/largest-palindrome-product/
 * @date 2022/4/16 17:06
 */
public class task_479 {
    public static void main( String[] args ) {
        System.out.println( largestPalindrome( 2 ) );
    }

    public static int largestPalindrome(int n) {
        if (n==1) {
            return 9;
        }
        long maxVal = (long) ((Math.pow( 10,n )-1)*(Math.pow( 10,n )-1));
        int num = getPalindromeNum(n,maxVal);
        long val = -1;
        big:for (int i = num; i > 0; i--) {
            val = getPalindrome( n, i );
            long maxNum = (int) (Math.pow( 10,n )-1);
            long minNum = (int) Math.pow( 10, n-1 );
            for (long k = maxNum; k > minNum; k--) {
                if (val/k>maxNum) {
                    continue big;
                }
                if (val%k==0) {
                    break big;
                }
            }
        }
        return (int) (val%1337);
    }

    //获取长度为2*half的第n个回文数
    private static long getPalindrome( int half, int n ) {
        long val = (int) (Math.pow( 10, half-1 ) + n);
        String str = String.valueOf( val );
        val = Long.parseLong( str.concat( new StringBuilder(str).reverse().toString() ) );
        return val;
    }

    //获取长度为2*half的回文数数量
    private static int getPalindromeNum( int half, long maxVal ) {
        long max = Long.parseLong( String.valueOf( maxVal ).substring( 0, half ) );
        return (int) (max - Math.pow( 10, half-1 ));
    }
}
