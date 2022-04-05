package com.wp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: wp
 * @Title: task_762  762. 二进制表示中质数个计算置位
 * @Description: https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/
 * @date 2022/4/5 11:51
 */
public class task_762 {
    public static void main( String[] args ) {
        System.out.println( countPrimeSetBits( 6, 10 ) );
    }
    public static int countPrimeSetBits(int left, int right) {
        Set<Integer> set = new HashSet<>();
        set.addAll( Arrays.asList(2,3,5,7,11,13,17,19) );
        int num = 0;
        for (; left <= right; left++) {
            int size = Integer.bitCount( left );
            if (set.contains( size )) {
                num++;
            }
        }
        return num;
    }
    public static boolean isPrime(int num) {
        /*
         * 质数定义：只有1和它本身两个因数的自然数
         *
         * 1. 小于等于1或者是大于2的偶数，直接返回false
         * 2. 2直接返回true
         * 3. 从3开始算起(每次加2，截止为输入值的平方根)，每次输入值除以前者，若出现一个能除尽则直接返回false
         * 4. 全都除不尽，则为质数，返回true
         * */
        if (num <= 1 || num > 2 && num % 2 == 0) {
            return false;
        } else if (num == 2) {
            return true;
        }
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
