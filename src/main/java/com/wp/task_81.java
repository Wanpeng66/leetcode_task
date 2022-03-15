package com.wp;

import java.lang.annotation.Target;

/**
 * @author: wp
 * @Title: task_81 81. 搜索旋转排序数组 II
 * @Description: https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 * @date 2022/3/14 11:16
 */
public class task_81 {
    public static void main( String[] args ) {
        int[] nums = {1,3};
        System.out.println( search( nums, 3 ) );
    }

    public static boolean search(int[] nums, int target) {
        int splitOffset = -1;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i]>nums[i+1]) {
                splitOffset = i;
                break;
            }
        }
        if (splitOffset==-1) {
            return binarySearch( nums,0,nums.length-1,target );
        }else{
            if (target<nums[0]) {
                return binarySearch( nums,splitOffset+1,nums.length-1,target );
            }else{
                return binarySearch( nums,0,splitOffset,target );
            }
        }
    }

    private static boolean binarySearch( int[] nums, int start, int end, int target ){
        while (start<=end) {
            int middle = end + (start-end)/2;
            if (nums[middle]>target) {
                end = middle-1;
            } else if (nums[middle]<target) {
                start = middle+1;
            }else{
                return true;
            }
        }
        return false;
    }
}
