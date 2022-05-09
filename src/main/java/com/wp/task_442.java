package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_442 442. 数组中重复的数据
 * @Description: https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 * @date 2022/5/8 8:49
 */
public class task_442 {
    public List<Integer> findDuplicates( int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort( nums );
        int pre = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==pre) {
                res.add( nums[i] );
            }else{
                pre = nums[i];
            }
        }
        return res;
    }
}
