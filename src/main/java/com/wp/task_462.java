package com.wp;

/**
 * @author: wp
 * @Title: task_462  462. 最少移动次数使数组元素相等 II
 * @Description: https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/
 * @date 2022/5/19 18:03
 */
public class task_462 {
    public static void main( String[] args ) {
        int[] nums = {1,0,0,8,6};
        System.out.println( minMoves2( nums ) );
    }
    public static int minMoves2(int[] nums) {
        long sum = 0l;
        for (int num : nums) {
            sum += num;
        }
        long tmp = sum / nums.length;
        int size = 0;
        for (int num : nums) {
            size+=(Math.abs( tmp-num ));
        }
        return size;
    }
}
