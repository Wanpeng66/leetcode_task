package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_743 743. 网络延迟时间
 * @Description: https://leetcode-cn.com/problems/network-delay-time/
 * @date 2022/2/24 16:27
 */
public class task_743 {
    public static void main( String[] args ) {
        //int[][] times = {{1,2,1},{2,3,2},{1,3,2}};
        //int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int[][] times = {{2,4,10},{5,2,38},{3,4,33},{4,2,76},{3,2,64},{1,5,54},{1,4,98},{2,3,61},{2,1,0},{3,5,77},{5,1,34},{3,1,79},{5,3,2},{1,2,59},{4,3,46},{5,4,44},{2,5,89},{4,5,21},{1,3,86},{4,1,95}};
        System.out.println( networkDelayTime( times, 5, 1 ) );
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer,Integer>[] adj = new HashMap[n+1];
        for (int[] time : times) {
            Map<Integer,Integer> map = adj[time[0]];
            if (null==map) {
                map = new HashMap<>();
                adj[time[0]] = map;
            }
            map.put( time[1], time[2] );
        }
        HashSet<Integer> visited = new HashSet<>();
        Map<Integer,Integer> nodeTime = new HashMap<>();
        nodeTime.put( k,0 );
        dfs(adj,k,nodeTime,visited);
        return visited.size()==n?nodeTime.values().stream().max( Comparator.naturalOrder() ).get():-1;
    }

    private static void dfs( Map<Integer, Integer>[] adj, Integer k, Map<Integer,Integer> nodeTime, HashSet<Integer> visited ) {
        visited.add( k );
        Map<Integer, Integer> map = adj[k];
        if (null!=map) {
            for (Integer key : map.keySet()) {
                Integer time = map.get( key )+nodeTime.getOrDefault( k, 0 );
                if (time<nodeTime.getOrDefault( key, Integer.MAX_VALUE )) {
                    nodeTime.put( key, time );
                }
                if (!visited.contains( key )) {
                    dfs( adj, key, nodeTime, visited );
                }
            }
        }
    }
}
