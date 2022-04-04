package com.wp;

import cn.hutool.core.io.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToIntFunction;

/**
 * @author: wp
 * @Title: task_5219 5219. 每个小孩最多能分到多少糖果
 * @Description: https://leetcode-cn.com/contest/weekly-contest-287/problems/maximum-candies-allocated-to-k-children/
 * @date 2022/4/3 11:12
 */
public class task_5219 {
    public static void main( String[] args ) {
        String str = FileUtil.readString( "C:\\Users\\wp\\Desktop\\test.txt", "UTF-8" );
        int[] candies = Arrays.stream( str.split( "," ) ).mapToInt( new ToIntFunction<String>() {
            @Override
            public int applyAsInt( String value ) {
                return Integer.parseInt( value );
            }
        } ).toArray();
        //int[] candies = {2,5};
        System.out.println( maximumCandies( candies, 9054792974l ) );
    }
    public static int maximumCandies(int[] candies, long k) {
        long sum = Arrays.stream( candies ).asLongStream().sum();
        if (sum<k) {
            return 0;
        }
        long left = 1;
        long right = Arrays.stream( candies ).max().getAsInt();
        Long res = 1L;
        while (left<=right) {
            long num = 0;
            long middle = (left+ right)/2;
            for (int i = 0; i < candies.length; i++) {
                num += candies[i]/middle;
            }
            if (num<k) {
                right = middle-1;
            } else{
                res = middle;
                left = middle+1;
            }
        }
        return res.intValue();
        /*int min = Arrays.stream( candies ).max().getAsInt();
        big:for(;min>=0;min--){
            int j = 0;
            for (int i = 0; i < candies.length; i++) {
                j += candies[i] / min;
                if (j>=k) {
                    break big;
                }
            }
        }
        return min;*/
    }
}
