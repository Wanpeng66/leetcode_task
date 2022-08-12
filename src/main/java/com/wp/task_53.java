package com.wp;

/**
 * @author: wp
 * @Title: task_53  53. 最大子数组和
 * @Description: https://leetcode.cn/problems/maximum-subarray/submissions/
 * @date 2022/8/12 22:10
 */
public class task_53 {
    public int maxSubArray(int[] nums) {
        int cur = nums[0];
        int total = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max( nums[i] , cur+nums[i] );
            total = Math.max( cur , total );
        }
        return total;
    }
}
