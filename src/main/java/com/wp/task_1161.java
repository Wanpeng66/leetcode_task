package com.wp;


import java.util.*;

/**
 * @author: wp
 * @Title: task_1161  1161. 最大层内元素和
 * @Description: https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree/
 * @date 2022/7/31 18:32
 */
public class task_1161 {
    public static void main( String[] args ) {
        TreeNode root = new TreeNode( -100, new TreeNode( -200, new TreeNode( -20 ), new TreeNode( -5 ) ), new TreeNode( -300, new TreeNode(-10), null ) );
        System.out.println( maxLevelSum( root ) );
    }
    public static int maxLevelSum(TreeNode root) {
        Integer minLevel = 0;
        Long max = Long.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        int level = 1;
        while (!queue.isEmpty()) {
            long sum = 0l;
            int size = queue.size();
            for (int i = size; i > 0; i--) {
                TreeNode poll = queue.poll();
                sum += poll.val;
                if (null!=poll.left) {
                    queue.offer( poll.left );
                }
                if (null!=poll.right) {
                    queue.offer( poll.right );
                }
            }
            if (sum>max) {
                max = sum;
                minLevel = level;
            }
            level++;
        }
        return minLevel;
    }
}
