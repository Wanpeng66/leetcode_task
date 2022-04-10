package com.wp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wp
 * @Title: task_851  851. 喧闹和富有
 * @Description: https://leetcode-cn.com/problems/loud-and-rich/
 * @date 2022/4/9 13:59
 */
public class task_851 {

    public static void main( String[] args ) {
        int[][] richer = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet = {3,2,5,4,6,1,7,0};
        int[] rich = loudAndRich( richer, quiet );
        System.out.println( rich );
    }
    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        Set<Integer>[] adj = new HashSet[quiet.length];
        for (int[] array : richer) {
            if (null==adj[array[1]]) {
                adj[array[1]] = new HashSet<>();
            }
            adj[array[1]].add( array[0] );
        }
        boolean[] isVisited = new boolean[quiet.length];
        for (int i = 0;i<adj.length;i++) {
            if (null==adj[i]) {
                continue;
            }
            Set<Integer> tmp = new HashSet<>();
            tmp.add( i );
            dfs(adj[i], adj, tmp, isVisited);
            adj[i] = tmp;
            isVisited[i] = true;
        }
        int[] res = new int[quiet.length];
        for (int i = 0; i < res.length; i++) {
            Set<Integer> set = adj[i];
            if (null==set || set.isEmpty()) {
                res[i] = i;
            }else{
                res[i] = getMin(set, quiet);
            }
        }
        return res;
    }

    private static int getMin( Set<Integer> set, int[] quiet ) {
        int index = -1;
        Integer min = Integer.MAX_VALUE;
        for (Integer i : set) {
            if (quiet[i]<min) {
                min = quiet[i];
                index = i;
            }
        }
        return index;
    }

    private static void dfs( Set<Integer> set, Set<Integer>[] adj, Set<Integer> tmp, boolean[] isVisited ) {
        if (null==set || set.isEmpty()) {
            return;
        }
        for (Integer i : set) {
            if (isVisited[i]) {
                tmp.addAll( adj[i] );
                continue;
            }
            tmp.add( i );
            dfs( adj[i], adj, tmp, isVisited );
        }
    }
}
