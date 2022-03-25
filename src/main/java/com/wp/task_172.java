package com.wp;

import java.math.BigDecimal;

/**
 * @author: wp
 * @Title: task_172 172. 阶乘后的零
 * @Description: https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 * @date 2022/3/25 9:35
 */
public class task_172 {
    public static void main( String[] args ) {
        System.out.println( trailingZeroes( 30 ) );
    }

    public static int trailingZeroes(int n) {
        Double res = dfs(n);
        char[] chars = String.valueOf( res ).toCharArray();
        int num = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i]=='0') {
                num++;
            }else{
                break;
            }
        }
        return num;
    }

    private static double dfs( long n ) {
        if (n<=1) {
            return 1;
        }
        return n * dfs( n-1 );
    }
}
