package com.wp;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author: wp
 * @Title: task_719  719. 找出第 K 小的数对距离
 * @Description: https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
 * @date 2022/6/15 14:03
 */
public class task_719 {
    public static void main( String[] args ) {
        int[] nums = {62,100,4};
        System.out.println( smallestDistancePair( nums, 2 ) );
    }
    public static int smallestDistancePair(int[] nums, int k) {
        int num = 0;
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int abs = Math.abs( nums[i] - nums[j] );
                map.put( abs, map.getOrDefault( abs,0 )+2 );
                num += 2;
            }
        }
        num = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if ((num+=entry.getValue())>=k) {
                return entry.getKey();
            }
        }

        return 0;
    }
}
