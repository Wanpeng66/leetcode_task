package com.wp;

import java.util.Arrays;

import static com.wp.Sort2.split;

/**
 * @author: wp
 * @Title: task_912 912. 排序数组
 * @Description: https://leetcode-cn.com/problems/sort-an-array/
 * @date 2022/2/1 12:54
 */
public class task_912 {

    public static void main( String[] args ) {
        int[] nums = {-1,2,-8,-10};
        sortArray( nums );
        System.out.println( nums );
    }

    public static int[] sortArray(int[] nums) {
        if (null==nums || 0 == nums.length) {
            return nums;
        }
        //split(nums, 0, nums.length-1);
        sort(nums, 0, nums.length-1);
        return nums;
    }

    private static void sort( int[] nums, int p, int r ) {
        if (p>=r) {
            return;
        }
        int q = partion(nums, p, r);
        sort( nums, p, q-1 );
        sort( nums, q+1, r );
    }

    private static int partion( int[] nums, int p, int r ) {
        int index = p;
        int val = nums[r];
        for(;p<r;p++){
            if (nums[p]<val) {
                int tmp = nums[p];
                nums[p] = nums[index];
                nums[index] = tmp;
                index++;
            }
        }
        nums[r] = nums[index];
        nums[index] = val;
        return index;
    }


    public void split(int[] nums, int p, int r){
        if (p>=r) {
            return;
        }
        int q = (p+r)/2;
        split( nums,p,q);
        split( nums,q+1,r );
        merge(nums, Arrays.copyOfRange( nums,p,q+1 ), Arrays.copyOfRange( nums,q+1,r+1 ), p );
    }

    private void merge( int[] nums, int[] one, int[] two, int p ) {
        int i1 = 0,i2 = 0;
        while (i1<one.length && i2<two.length) {
            if (one[i1]<=two[i2]) {
                nums[p++] = one[i1++];
            }else{
                nums[p++] = two[i2++];
            }
        }
        while (i1<one.length) {
            nums[p++] = one[i1++];
        }
        while (i2< two.length) {
            nums[p++] = two[i2++];
        }
    }
}
