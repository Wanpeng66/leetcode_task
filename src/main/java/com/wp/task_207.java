package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_207  207. 课程表
 * @Description: https://leetcode-cn.com/problems/course-schedule/
 * @date 2022/2/18 10:41
 */
public class task_207 {

    public static void main( String[] args ) {
        int[][] prerequisites = {{0,1},{0,2},{1,2},{2,1}};
        System.out.println( canFinish( 3, prerequisites ) );
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> list = map.getOrDefault( prerequisite[1], new ArrayList<>() );
            list.add( prerequisite[0] );
            map.put( prerequisite[1], list );
        }
        for (Integer key : map.keySet()) {
            int num = 0;
            Queue<Integer> queue = new LinkedList<>();
            List<Integer> list = map.get( key );
            for (Integer integer : list) {
                queue.offer( integer );
            }
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Integer poll = queue.poll();
                    num++;
                    if (poll.equals( key )) {
                        return false;
                    }
                    if (num>numCourses) {
                        continue;
                    }

                    list = map.get( poll );
                    if (null!=list) {
                        for (Integer integer : list) {
                            queue.offer( integer );
                        }
                    }
                }
            }
        }
        return true;
    }
}
