package com.wp;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author: wp
 * @Title: task_908  908. 最小差值 I
 * @Description: https://leetcode-cn.com/problems/smallest-range-i/
 * @date 2022/4/30 12:08
 */
public class task_908 {
    public static void main( String[] args ) {
        int[] nums = {10, 7, 1};
        System.out.println( smallestRangeI( nums, 3 ) );
    }
    public static int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num<min) {
                min = num;
            }
            if (num>max) {
                max = num;
            }
        }

        if (min+k>=max || min+k >= max-k) {
            return 0;
        }else{
            return max - k - min - k;
        }
    }
}
