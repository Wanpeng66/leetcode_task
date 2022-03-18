package com.wp;

import java.util.ArrayList;

/**
 * @author: wp
 * @Title: task_1466  1466. 重新规划路线
 * @Description: https://leetcode-cn.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 * @date 2022/3/18 9:51
 */
public class task_1466 {
    public static void main( String[] args ) {
        int n = 6;
        int[][] connections = {{0,1},{1,3},{2,3},{4,0},{4,5}};
        System.out.println( minReorder( n, connections ) );
    }

    public static int minReorder(int n, int[][] connections) {
        ArrayList<Integer>[] adj = new ArrayList[n];
        ArrayList<Integer>[] array = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            array[i] = new ArrayList<>();
        }
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            adj[from].add( to );
            array[from].add( to );
            array[to].add( from );
        }
        int[] num = {0};
        boolean[] visited = new boolean[n];
        dfs(0,array,adj,num,visited);
        return num[0];
    }

    private static void dfs( int i, ArrayList<Integer>[] array, ArrayList<Integer>[] adj, int[] num, boolean[] visited ) {
        visited[i] = true;
        ArrayList<Integer> list = array[i];
        for (int j = 0; j < list.size(); j++) {
            Integer k = list.get( j );
            if (visited[k]) {
                continue;
            }
            if (!adj[k].contains( i )) {
                num[0]++;
            }
            dfs( k,array,adj,num, visited );
        }
    }
}
