package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_1462  1462. 课程表 IV
 * @Description: https://leetcode-cn.com/problems/course-schedule-iv/
 * @date 2022/3/17 14:37
 */
public class task_1462 {

    public static void main( String[] args ) {
        int numCourses = 7;
        int[][] prerequisites = {{2,3},{2,1},{2,0},{3,4},{3,6},{5,1},{5,0},{1,4},{1,0},{4,0},{0,6}};
        int[][] queries = {{3,0}};
        List<Boolean> list = checkIfPrerequisite( numCourses, prerequisites, queries );
        System.out.println( list );
    }

    public static List<Boolean> checkIfPrerequisite( int numCourses, int[][] prerequisites, int[][] queries) {
        LinkedList<Integer>[] inverseAdj = new LinkedList[numCourses];
        HashSet<Integer>[] map = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            inverseAdj[i] = new LinkedList<>();
              map[i] = new HashSet<>();
        }
        for (int[] prerequisite : prerequisites) {
            inverseAdj[prerequisite[1]].add( prerequisite[0] );
        }
        bfs( numCourses,inverseAdj,map );
        /*boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                dfs( i,inverseAdj,visited,map );
            }
        }*/
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int from = query[0];
            int to = query[1];
            res.add( map[to].contains( from ) );
        }
        return res;
    }

    private static void dfs( int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited, HashSet<Integer>[] map ) {
        visited[vertex] = true;
        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (!visited[w]){
                dfs(w, inverseAdj, visited, map );
            }
            map[vertex].add( w );
            map[vertex].addAll( map[w] );
        }
    }

    private static void bfs(int numCourses,LinkedList<Integer>[] inverseAdj,HashSet<Integer>[] map){
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer( i );
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    Integer poll = queue.poll();
                    if (poll!=i) {
                        map[i].add( poll );
                    }
                    if (visited[poll]) {
                        map[i].addAll( map[poll] );
                        continue;
                    }

                    LinkedList<Integer> list = inverseAdj[poll];
                    for (Integer num : list) {
                        queue.offer( num );
                    }
                }
            }
            visited[i] = true;
        }
    }
}
