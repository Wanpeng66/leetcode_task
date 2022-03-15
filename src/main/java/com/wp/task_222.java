package com.wp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_222    222. 完全二叉树的节点个数
 * @Description: https://leetcode-cn.com/problems/count-complete-tree-nodes/
 * @date 2022/3/15 10:13
 */
public class task_222 {

    public int countNodes(TreeNode root) {
        int total = 0;
        if (null==root) {
            return total;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        while (!queue.isEmpty()) {
            int size = queue.size();
            total += size;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (null!=poll.left) {
                    queue.offer( poll.left );
                }
                if (null!=poll.right) {
                    queue.offer( poll.right );
                }
            }
        }
        return total;
    }
}
