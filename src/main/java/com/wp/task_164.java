package com.wp;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_164   164. 最大间距
 * @Description:  https://leetcode-cn.com/problems/maximum-gap/
 * @date 2022/3/15 10:38
 */
public class task_164 {

    public static void main( String[] args ) {

        int[] nums = {3,6,9,1};
        long l = System.currentTimeMillis();
        System.out.println( maximumGap( nums ) );
        System.out.println(System.currentTimeMillis() - l);
    }

    public static int maximumGap(int[] nums) {
        if (nums.length<2) {
            return 0;
        }
        if (nums.length==2) {
            return Math.abs( nums[0]-nums[1] );
        }
        Integer max = Arrays.stream( nums ).max().getAsInt();
        int length = String.valueOf( max ).length();
        nums = radixSort(nums,length);
        int gap = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            int tmp = nums[i] - nums[i - 1];
            if (tmp>gap) {
                gap = tmp;
            }
        }
        return gap;
    }

    private static int[] radixSort( int[] nums, int length ) {
        for (int i = 1; i <= length; i++) {
            nums = countingSort( nums, i);
        }
        return nums;
    }

    private static int[] countingSort( int[] nums, int b) {
        int[] countArray = new int[10];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = getValue( nums[i], b );
            countArray[val]+=1;
            map.put( nums[i],val );
        }
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i-1];
        }
        int[] res = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int val = map.get( nums[i] );
            res[countArray[val]-1] = nums[i];
            countArray[val] -= 1;
        }
        return res;
    }

    private static int getValue( int num, int b ) {
        return num / (int)(Math.pow( 10,b-1 )) % 10;
    }
}
