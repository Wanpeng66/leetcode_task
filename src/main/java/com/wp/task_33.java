package com.wp;

/**
 * @author: wp
 * @Title: task_33 33. 搜索旋转排序数组
 * @Description: https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * @date 2022/2/8 13:07
 */
public class task_33 {
    public static void main( String[] args ) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println( search( nums, 3 ) );
    }

    public static int search(int[] nums, int target) {
        return recursion( nums, 0, nums.length - 1, target );

    }

    private static int recursion( int[] nums, int start, int end, int target ) {
        if (start==end) {
            return nums[start]==target?start:-1;
        }
        int middle = (start + end)/2;
        if (nums[middle]==target) {
            return middle;
        }
        int max = end,min = start;
        boolean flag = false;
        if (nums[start]< nums[middle]) {
            max = middle - 1;
            flag = true;
        }else{
            min = middle + 1;
        }
        if (nums[min]<= target && nums[max]>= target) {
            return binarySearch( nums, min, max, target );
        }else{
            if (flag) {
                return recursion( nums, middle,end, target );
            }else{
                return recursion( nums,start,middle, target );
            }
        }
    }

    private static int binarySearch( int[] nums, int min, int max, int target ) {
        while (min<=max) {
            int middle = min + (max - min)/2;
            if (nums[middle]> target) {
                max = --middle;
            } else if (nums[middle]< target) {
                min = ++middle;
            }else{
                return middle;
            }
        }
        return  -1;
    }

    private int method1( int[] nums, int target ) {
        int split = -1;
        int min = 0,max = nums.length-1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                split = i - 1;
            }
        }
        if (split!=-1) {
            if (nums[0]> target) {
                min = split+1;
            } else if (nums[nums.length-1]< target) {
                max = split;
            } else if (nums[0]== target) {
                return min;
            } else if (nums[nums.length-1]== target) {
                return max;
            }
        }
        while (min<=max) {
            int middle = min + (max - min)/2;
            if (nums[middle]> target) {
                max = --middle;
            } else if (nums[middle]< target) {
                min = ++middle;
            }else{
                return middle;
            }
        }
        return  -1;
    }
}
