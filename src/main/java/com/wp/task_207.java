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
        int[][] prerequisites = {{1,0}};
        System.out.println( canFinish2( 2, prerequisites ) );
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

    //
    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> map = new HashMap<>(numCourses);
        int[] preArray = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int key = prerequisite[1];
            int val = prerequisite[0];
            List<Integer> orDefault = map.getOrDefault( key, new ArrayList<>() );
            orDefault.add( val );
            map.put( key,orDefault );
            preArray[val] += 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < preArray.length; i++) {
            if (preArray[i]==0) {
                queue.offer( i );
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                System.out.println(poll);
                List<Integer> list = map.get( poll );
                if (null!=list) {
                    for (Integer offset : list) {
                        preArray[offset] -= 1;
                        if (preArray[offset]==0) {
                            queue.offer( offset );
                        }
                    }
                }
            }
        }
        for (int i : preArray) {
            if (i!=0) {
                return false;
            }
        }
        return true;
    }

}
