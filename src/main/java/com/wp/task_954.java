package com.wp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: task_954  954. 二倍数对数组
 * @Description: https://leetcode-cn.com/problems/array-of-doubled-pairs/
 * @date 2022/4/1 19:53
 */
public class task_954 {
    public static void main( String[] args ) {
        int[] arr = {2,4,0,0,8,1};
        System.out.println( canReorderDoubled( arr ) );
    }

    public static boolean canReorderDoubled(int[] arr) {
        int num = 0;
        List<Integer> collect = Arrays.stream( arr ).boxed().collect( Collectors.toList() );
        collect.sort( ( o1, o2 ) -> Math.abs( o1 )-Math.abs( o2 ) );
        Map<Integer,Integer> map = new HashMap<>();
        for (Integer i : collect) {
            map.put( i, map.getOrDefault( i,0 )+1 );
        }
        for (int j = 0; j < collect.size(); j++) {
            Integer i = collect.get( j );
            if (i==0 && map.get( 0 )<2) {
                continue;
            }
            if (map.containsKey( 2*i ) && map.get( 2*i )>=1 && map.get( i )>=1) {
                map.put( 2*i,map.get( 2*i )-1 );
                map.put( i, map.get( i )-1 );
                num++;
            }
        }
        return num == arr.length/2;
    }

    private static boolean method1( int[] arr ) {
        int num = 0;
        Map<Integer,Integer> map = new HashMap<>();
        Arrays.sort( arr );
        for (int i : arr) {
            Integer orDefault = map.getOrDefault( i, 0 );
            map.put( i,orDefault+1 );
        }
        for (int i : arr) {
            if (i==0 && map.get( 0 )<2) {
                continue;
            }
            if (map.containsKey( 2*i ) && map.get( 2*i )>=1 && map.get( i )>=1) {
                map.put( 2*i,map.get( 2*i )-1 );
                map.put( i, map.get( i )-1 );
                num++;
            }
        }
        return num>= arr.length/2;
    }
}
