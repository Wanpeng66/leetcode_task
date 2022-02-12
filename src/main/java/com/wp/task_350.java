package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_350  350. 两个数组的交集 II
 * @Description: https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 * @date 2022/2/5 15:52
 */
public class task_350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map1 = new HashMap<>();
        for (int num : nums1) {
            Integer orDefault = map1.getOrDefault( num, 0 );
            map1.put( num,++orDefault );
        }
        Map<Integer,Integer> map2 = new HashMap<>();
        for (int num : nums2) {
            Integer orDefault = map2.getOrDefault( num, 0 );
            map2.put( num,++orDefault );
        }
        Map<Integer,Integer> l = map1.size()>map2.size()?map2:map1;
        Map<Integer,Integer> b = map1.size()>map2.size()?map1:map2;
        Set<Integer> keySet = l.keySet();
        keySet.retainAll( b.keySet() );
        List<Integer> res = new ArrayList<>();
        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            for(int i=0;i<(map1.get( next )<map2.get( next )?map1.get( next ):map2.get( next ));i++){
                res.add( next );
            }
        }
        int[] tmp = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            tmp[i] = res.get( i );
        }
        return tmp;
    }
}
