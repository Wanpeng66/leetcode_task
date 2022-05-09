package com.wp;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author: wp
 * @Title: task_713  713. 乘积小于 K 的子数组
 * @Description: https://leetcode-cn.com/problems/subarray-product-less-than-k/
 * @date 2022/5/5 18:38
 */
public class task_713 {
    public static void main( String[] args ) {
        int[] nums = {10,5,2,6};
        System.out.println( numSubarrayProductLessThanK( nums, 100 ) );
    }
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = 0;
        int start = 0,end = 0,val = 1;
        for (; start < nums.length; ) {
            if (end<nums.length) {
                val = val*nums[end];
                if (val < k) {
                    n++;
                    System.out.println( Arrays.toString( Arrays.copyOfRange( nums, start, end+1 ) ) );
                }else{
                    while (val>=k) {
                        val = val / nums[start++];
                        if (val<k) {
                            n++;
                            System.out.println( Arrays.toString( Arrays.copyOfRange( nums, start, end ) ) );
                        }
                    }
                }
                end++;
            }else{
                val = val / nums[start++];
                if (val<k && start!=end) {
                    n++;
                    System.out.println( Arrays.toString( Arrays.copyOfRange( nums, start, end ) ) );
                }
            }

        }
        return n;
    }
}
