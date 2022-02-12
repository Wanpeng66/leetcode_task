package com.wp;

/**
 * @author: wp
 * @Title: task_268 268. 丢失的数字
 * @Description: https://leetcode-cn.com/problems/missing-number/
 * @date 2022/2/5 15:15
 */
public class task_268 {

    public static void main( String[] args ) {
        int[] nums = {3,0,2};
        System.out.println( missingNumber( nums ) );
    }

    public static int missingNumber(int[] nums) {
        int[] tmp = new int[nums.length+1];
        for (int num : nums) {
            tmp[num] += 1;
        }
        int k = 0;
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i]==0) {
                k = i;
                break;
            }
        }
        return k;
    }
}
