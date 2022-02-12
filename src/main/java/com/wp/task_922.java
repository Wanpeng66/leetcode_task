package com.wp;

import java.util.Stack;

/**
 * @author: wp
 * @Title: task_922  922. 按奇偶排序数组 II
 * @Description: https://leetcode-cn.com/problems/sort-array-by-parity-ii/
 * @date 2022/1/30 13:26
 */
public class task_922 {

    public static int[] sortArrayByParityII(int[] nums) {
        if (null==nums || nums.length==0) {
            return nums;
        }
        Stack<Integer> os = new Stack<>();
        Stack<Integer> js = new Stack<>();
        for (int num : nums) {
            if (num%2==0) {
                os.push( num );
            }else{
                js.push( num );
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i%2==0) {
                nums[i] = os.pop();
            }else{
                nums[i] = js.pop();
            }
        }
        return nums;
    }
}
