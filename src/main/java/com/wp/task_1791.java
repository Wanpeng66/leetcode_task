package com.wp;

/**
 * @author: wp
 * @Title: task_1791 1791. 找出星型图的中心节点
 * @Description: https://leetcode-cn.com/problems/find-center-of-star-graph/
 * @date 2022/2/15 16:08
 */
public class task_1791 {

    public int findCenter(int[][] edges) {
        int n = edges.length+1;
        int[] num = new int[n+1];
        for (int[] edge : edges) {
            num[edge[0]]++;
            num[edge[1]]++;
        }
        for (int i = 0; i <= n; i++) {
            if (num[i]==n-1) {
                return i;
            }
        }
        return -1;
    }
}
