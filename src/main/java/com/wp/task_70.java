package com.wp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_70 70. 爬楼梯
 * @Description: https://leetcode.cn/problems/climbing-stairs/
 * @date 2022/8/13 11:16
 */
public class task_70 {
    public static void main( String[] args ) {
        System.out.println( climbStairs( 45 ) );
    }
    public static int climbStairs(int n) {
        Map<Integer,Integer> cache = new HashMap<>();
        return dfs(n, cache);
    }

    private static int dfs( int n, Map<Integer, Integer> cache ) {
        if (n<=2) {
            return n;
        }
        if (cache.containsKey( n )) {
            return cache.get( n );
        }
        int one = dfs( n - 1, cache );
        cache.put( n-1, one );
        int two = dfs( n - 2, cache );
        cache.put( n-2, two );
        return one + two;
    }
}
