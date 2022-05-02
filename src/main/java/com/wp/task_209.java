package com.wp;

/**
 * @author: wp
 * @Title: task_209 209. 长度最小的子数组
 * @Description: https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * @date 2022/5/2 9:35
 */
public class task_209 {
    public static void main( String[] args ) {
        int[] nums = {1,1,1,1,1,1,1,1};
        System.out.println( minSubArrayLen( 11, nums ) );
    }
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0,right = 0;
        int size = Integer.MAX_VALUE,sum = 0;
        for (; right < nums.length; right++) {
            sum += nums[right];
            while (sum>=target) {
                int length = right - left + 1;
                if (length<size) {
                    size = length;
                }
                sum -= nums[left++];
            }
        }
        return size==Integer.MAX_VALUE?0:size;
    }
}
