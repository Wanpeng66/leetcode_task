package com.wp;

/**
 * @author: wp
 * @Title: task_378  378. 有序矩阵中第 K 小的元素
 * @Description:  https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * @date 2022/2/5 14:55
 */
public class task_378 {

    public int kthSmallest(int[][] matrix, int k) {
        int[] offsets = new int[matrix.length];
        int n = 0;
        int res = 0;
        while (n<k) {
            res = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < matrix.length; i++) {
                int[] array = matrix[i];
                if (offsets[i]>=array.length) {
                    continue;
                }
                int val = array[offsets[i]];
                if (val<res) {
                    res = val;
                    index = i;
                }
            }
            offsets[index] += 1;
            n++;
        }
        return res;
    }
}
