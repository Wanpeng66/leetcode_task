package com.wp;

import java.util.Stack;

/**
 * @author: wp
 * @Title: task_5236 5236. 美化数组的最少删除数
 * @Description: https://leetcode-cn.com/contest/weekly-contest-286/problems/minimum-deletions-to-make-array-beautiful/
 * @date 2022/3/27 11:32
 */
public class task_5236 {
    public static void main( String[] args ) {
        int[] nums = {1,1,2,2,3,3};
        System.out.println( minDeletion( nums ) );
    }
    public static int minDeletion(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            int size = stack.size();
            if (size%2==0) {
                stack.push( num );
            }else{
                if (stack.peek()!=num) {
                    stack.push( num );
                }
            }
        }
        int size = stack.size();
        if (size%2!=0) {
            size--;
        }
        return nums.length - size;
    }
}
