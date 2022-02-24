package com.wp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_210  210. 课程表 II
 * @Description: https://leetcode-cn.com/problems/course-schedule-ii/
 * @date 2022/2/24 10:40
 */
public class task_210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        ArrayList<Integer> adj[] = new ArrayList[numCourses];
        int[] preArray = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int key = prerequisite[1];
            int val = prerequisite[0];
            preArray[val] += 1;
            ArrayList<Integer> list = adj[key];
            if (null==list) {
                list = new ArrayList<>();
                adj[key] = list;
            }
            list.add( val );
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < preArray.length; i++) {
            if (preArray[i]==0) {
                queue.offer( i );
            }
        }
        int k = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                res[k] = poll;
                k++;
                ArrayList<Integer> list = adj[poll];
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
                return new int[0];
            }
        }
        return res;
    }
}
