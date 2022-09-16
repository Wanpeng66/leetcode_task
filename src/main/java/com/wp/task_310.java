package com.wp;


import java.util.*;

/**
 * @author: wp
 * @Title: task_310  310. 最小高度树
 * @Description: https://leetcode-cn.com/problems/minimum-height-trees/
 * @date 2022/3/16 9:23
 */
public class task_310 {

    public static void main( String[] args ) {
        int n = 6;
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        List<Integer> list = findMinHeightTrees2( n, edges );
        System.out.println( list );
    }

    public static List<Integer> findMinHeightTrees2( int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] degree = new int[n];
        List<Integer> list = new ArrayList<>();
        if (n <= 2) {
            for (int i = 0; i < n; i++) {
                list.add( i );
            }
            return list;
        }
        for (int i = 0; i < n; i++) {
            adj.put( i, new ArrayList<>() );
        }
        for (int[] edge : edges) {
            adj.get( edge[0] ).add( edge[1] );
            adj.get( edge[1] ).add( edge[0] );
            degree[edge[0]] += 1;
            degree[edge[1]] += 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < degree.length; i++) {
            if (degree[i]==1) {
                queue.offer( i );
                visited[i] = true;
            }
        }
        while (!queue.isEmpty()) {
            list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0;i<size ; i++) {
                Integer poll = queue.poll();
                list.add( poll );
                List<Integer> nums = adj.get( poll );
                for (Integer num : nums) {
                    if (visited[num]) {
                        continue;
                    }
                    degree[num]--;
                    if (degree[num]==1) {
                        queue.offer( num );
                        visited[num] = true;
                    }
                }
            }
        }
        return list;
    }

    public static List<Integer> findMinHeightTrees( int n, int[][] edges) {
        Map<Integer,List<Integer>> adj = new HashMap<>();
        int[] degree = new int[n];
        List<Integer> list = new ArrayList<>();
        if (n<=2) {
            for (int i = 0; i < n; i++) {
                list.add( i );
            }
            return list;
        }
        for (int i = 0; i < n; i++) {
            adj.put( i,new ArrayList<>() );
        }
        for (int[] edge : edges) {
            adj.get( edge[0] ).add( edge[1] );
            adj.get( edge[1] ).add( edge[0] );
            degree[edge[0]] += 1;
            degree[edge[1]] += 1;
        }
        List<Integer> zeroList = new ArrayList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i]<=1) {
                zeroList.add( i );
            }
        }
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = adj.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            if (entry.getValue().size()<=1) {
                iterator.remove();
            }else{
                entry.getValue().removeAll( zeroList );
            }
        }
        int min = Integer.MAX_VALUE;
        Iterator<Integer> keyIterator = adj.keySet().iterator();
        big:while (keyIterator.hasNext()) {
            Integer i = keyIterator.next();
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            visited[i] = true;
            queue.offer( i );
            int height = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    Integer poll = queue.poll();
                    List<Integer> nums = adj.get( poll );
                    for (int j = 0; j < nums.size(); j++) {
                        Integer num = nums.get( j );
                        if (!visited[num]) {
                            queue.offer( num );
                            visited[num] = true;
                        }
                    }
                }
                height++;
                if (height>min) {
                    continue big;
                }
            }
            if (height==min) {
                list.add( i );
            }
            if (height<min) {
                min = height;
                list.clear();
                list.add( i );
            }
        }
        return list;
    }
}
