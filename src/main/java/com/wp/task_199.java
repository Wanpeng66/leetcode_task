package com.wp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_199  199. 二叉树的右视图
 * @Description: https://leetcode-cn.com/problems/binary-tree-right-side-view/
 * @date 2022/2/18 10:19
 */
public class task_199 {

    public List<Integer> rightSideView( TreeNode root) {
        if (null==root) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode node = null;
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if (null!=node.left) {
                    queue.offer( node.left );
                }
                if (null!=node.right) {
                    queue.offer( node.right );
                }
            }
            list.add( node.val );
        }
        return list;
    }
}
