package com.wp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_102  102. 二叉树的层序遍历
 * @Description: https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @date 2022/2/10 18:14
 */
public class task_102 {

    public List<List<Integer>> levelOrder( TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (null==root) {
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            lists.add( list );
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add( poll.val );
                if (null!=poll.left) {
                    queue.offer( poll.left );
                }
                if (null!=poll.right) {
                    queue.offer( poll.right );
                }
            }
        }
        return lists;
    }
}
