package com.wp;

/**
 * @author: wp
 * @Title: task_34  34. 在排序数组中查找元素的第一个和最后一个位置
 * @Description: https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @date 2022/2/7 12:34
 */
public class task_34 {

    public static void main( String[] args ) {
        int[] nums = {1};
        int[] ints = searchRange( nums, 1 );
        System.out.println( ints );
    }

    public static int[] searchRange(int[] nums, int target) {
        int min = 0;
        int max = nums.length-1;
        int first = -1,last = -1;
        while (min<=max) {
            int middle = min + (max - min) / 2;
            if (nums[middle]>target) {
                max = --middle;
            } else if (nums[middle]<target) {
                min = ++middle;
            }else{
                if (middle==0 || (nums[middle-1]!=target)) {
                    first = middle;
                    break;
                }else{
                    max = --middle;
                }
            }
        }
        if (first==-1) {
            return new int[]{first, last};
        }
        for (int i = first; i < nums.length; i++) {
            if(nums[i]==target){
                last = i ;
            }
        }
        return new int[]{first, last};
    }
}
