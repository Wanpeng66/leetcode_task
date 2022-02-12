package com.wp;

/**
 * @author: wp
 * @Title: task_35 35. 搜索插入位置
 * @Description: https://leetcode-cn.com/problems/search-insert-position/
 * @date 2022/2/6 15:11
 */
public class task_35 {

    public static void main( String[] args ) {
        int[] nums = {1,3,5,6};
        System.out.println( searchInsert( nums, 7 ) );
    }
    public static int searchInsert(int[] nums, int target) {
        int min = 0;
        int max = nums.length-1;
        int middle = 0;
        while (min<=max) {
            middle = min + (max - min)/2;
            if (nums[middle]==target) {
                return middle;
            } else if (nums[middle] > target) {
                max = --middle;
            }else{
                min = ++middle;
            }
        }
        if (middle >= nums.length) {
            return nums.length;
        } else if (middle<0) {
            return 0;
        }
        int offset = 0;
        if (nums[middle]<target) {
            for (int i = middle+1; i < nums.length; i++) {
                if (nums[i]>target) {
                    offset = i;
                    break;
                }
            }
        }else{
            for (int i = middle-1; i >= 0; i--) {
                if (nums[i]<target) {
                    offset =  i+1;
                    break;
                }
            }
        }
        return offset;
    }
}
