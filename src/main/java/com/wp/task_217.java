package com.wp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_217 217. 存在重复元素
 * @Description: https://leetcode-cn.com/problems/contains-duplicate/
 * @date 2022/2/3 13:39
 */
public class task_217 {

    public boolean containsDuplicate(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey( num )) {
                return true;
            }else{
                map.put( num,0 );
            }
        }
        return false;
    }
}
