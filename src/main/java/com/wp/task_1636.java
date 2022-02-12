package com.wp;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: task_1636  1636. 按照频率将数组升序排序
 * @Description: https://leetcode-cn.com/problems/sort-array-by-increasing-frequency/
 * @date 2022/1/30 13:39
 */
public class task_1636 {

    public static void main( String[] args ) {
        int[] nums = {-1,1,-6,4,5,-6,1,4,1};
        nums = frequencySort( nums );
        System.out.println( nums );
    }

    public static int[] frequencySort(int[] nums) {
        if (null==nums || nums.length==0) {
            return nums;
        }
        int tmp = 100;
        int[] f = new int[201];
        for (int num : nums) {
            f[num+tmp] = f[num+tmp]+1;
        }
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < f.length; i++) {
            if (f[i]>0) {
                List<Integer> list = map.getOrDefault( f[i], new ArrayList<>() );
                list.add( i );
                map.put( f[i],list );
            }
        }
        Set<Integer> keyset = map.keySet().stream().sorted().collect( Collectors.toCollection( LinkedHashSet :: new ) );
        int offset = 0;
        for (Integer key : keyset) {
            List<Integer> list = map.get( key );
            list.sort( Comparator.reverseOrder() );
            for (Integer num : list) {
                for (Integer i = 0; i < key; i++) {
                    nums[offset++] = num-tmp;
                }
            }
        }
        return nums;
    }
}
