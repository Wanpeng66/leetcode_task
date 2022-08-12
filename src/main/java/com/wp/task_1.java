package com.wp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_1  https://leetcode.cn/problems/two-sum/
 * @Description: https://leetcode.cn/problems/two-sum/
 * @date 2022/8/11 21:04
 */
public class task_1 {
    public static void main( String[] args ) {
        int[] nums = {1,1,1,1,1,4,1,1,1,1,1,7,1,1,1,1,1};
        int[] sum = twoSum( nums, 11 );
        System.out.println( sum );
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int j = target - num;
            if (map.containsKey( j )) {
                return new int[]{map.get( j ), i};
            }
            map.put( num, i );
        }
        return null;
    }
}
