package com.wp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_238  238. 除自身以外数组的乘积
 * @Description: https://leetcode.cn/problems/product-of-array-except-self/
 * @date 2022/8/11 22:02
 */
public class task_238 {
    public int[] productExceptSelf(int[] nums) {
        Map<Integer,Integer> offset2num = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            offset2num.put( i, nums[i] );
        }
        for (int i = nums.length - 1; i >= 0; i--) {

        }
        return null;
    }
}
