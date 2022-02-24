package com.wp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_802 802. 找到最终的安全状态
 * @Description: https://leetcode-cn.com/problems/find-eventual-safe-states/
 * @date 2022/2/24 11:17
 */
public class task_802 {

    public List<Integer> eventualSafeNodes( int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int[] outDegree = new int[graph.length];
        ArrayList<Integer> adj[] = new ArrayList[graph.length];
        for (int i = 0; i < graph.length; i++) {
            int[] ints = graph[i];
            outDegree[i] = ints.length;
            for (int val : ints) {
                ArrayList<Integer> list = adj[val];
                if (null==list) {
                    list = new ArrayList<>();
                    adj[val] = list;
                }
                list.add( i );
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < outDegree.length; i++) {
            if (outDegree[i]==0) {
                queue.offer( i );
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                ArrayList<Integer> list = adj[poll];
                if (null!=list) {
                    for (Integer offset : list) {
                        outDegree[offset] -= 1;
                        if (outDegree[offset]==0) {
                            queue.offer( offset );
                        }
                    }
                }
            }
        }
        for (int i = 0; i < outDegree.length; i++) {
            if (outDegree[i]==0) {
                res.add( i );
            }
        }
        return res;
    }
}
