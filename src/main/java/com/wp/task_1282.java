package com.wp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_1282  1282. 用户分组
 * @Description: https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/
 * @date 2022/8/12 21:24
 */
public class task_1282 {
    public static void main( String[] args ) {
        int[] groupSizes = {3,3,3,3,3,1,3};
        List<List<Integer>> list = groupThePeople( groupSizes );
        System.out.println( list );
    }
    public static List<List<Integer>> groupThePeople( int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer,List<Integer>> size2offset = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            List<Integer> list = size2offset.getOrDefault( size, new ArrayList<>() );
            list.add( i );
            size2offset.putIfAbsent( size, list );
        }
        for (Integer size : size2offset.keySet()) {
            List<Integer> list = size2offset.get( size );
            if (list.size()<=size) {
                res.add( list );
            }else{
                int i = list.size() / size;
                for (int j = 0; j < i; j++) {
                    List<Integer> sonList = new ArrayList<>();
                    for (int k = j * size; k < j * size + size; k++) {
                        sonList.add( list.get( k ) );
                    }
                    res.add( sonList );
                }
            }
        }
        return res;
    }
}
