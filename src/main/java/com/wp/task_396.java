package com.wp;

/**
 * @author: wp
 * @Title: task_396  396. 旋转函数
 * @Description: https://leetcode-cn.com/problems/rotate-function/
 * @date 2022/4/22 9:59
 */
public class task_396 {
    public static void main( String[] args ) {
        int[] nums = {100};
        System.out.println( maxRotateFunction( nums ) );
    }

    public static int maxRotateFunction(int[] nums) {
        int k = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            int sum = calculate(nums, i);
            if (sum>max) {
                max = sum;
            }
        }
        return max;
    }

    private static int calculate( int[] nums, int i ) {
        int length = nums.length;
        int sum = 0;
        int size = 0;
        int offset = ( length - i ) % length;
        while (size<length) {
            sum += size * nums[offset];
            size++;
            offset = ++offset % length;
        }
        return sum;
    }
}
