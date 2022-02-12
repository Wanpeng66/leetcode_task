package com.wp;

import java.util.Stack;

/**
 * @author: wp
 * @Title: task_905   905. 按奇偶排序数组
 * @Description: https://leetcode-cn.com/problems/sort-array-by-parity/
 * @date 2022/1/30 13:06
 */
public class task_905 {

    public static void main( String[] args ) {
        int[] nums = {3,1,2,4};
        nums = sortArrayByParity2( nums );
        System.out.println( nums.toString() );
    }

    public static int[] sortArrayByParity2(int[] nums) {
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
        int offset = 0;
        while (!os.isEmpty()) {
            nums[offset++] = os.pop();
        }
        while (!js.isEmpty()) {
            nums[offset++] = js.pop();
        }
        return nums;
    }

    public static int[] sortArrayByParity(int[] nums) {
        if (null==nums || nums.length==0) {
            return nums;
        }
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int val = 0;
            int offset = -1;
            for(int j=i;j<size;j++){
                if (nums[j]%2==0) {
                    val = nums[j];
                    offset = j;
                    break;
                }
            }
            if (-1 == offset) {
                break;
            }
            nums[offset] = nums[i];
            nums[i] = val;
            offset = -1;
        }
        return nums;
    }
}
