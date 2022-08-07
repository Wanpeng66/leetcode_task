package com.wp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_623  623. 在二叉树中增加一行
 * @Description: https://leetcode.cn/problems/add-one-row-to-tree/
 * @date 2022/8/5 10:10
 */
public class task_623 {
    public static void main( String[] args ) {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(3), new TreeNode(1)), new TreeNode(6, new TreeNode(5), null));
        root = addOneRow( root, 1, 3 );
        System.out.println( root );
    }
    public static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth==1) {
            return new TreeNode( val, root, null );
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (Math.abs( level-depth )==1) {
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    TreeNode left = poll.left;
                    if (null!=left) {
                        TreeNode node = new TreeNode( val );
                        poll.left = node;
                        node.left = left;
                    }else{
                        poll.left = new TreeNode(val);
                    }
                    TreeNode right = poll.right;
                    if (null!=right) {
                        TreeNode node = new TreeNode( val );
                        poll.right = node;
                        node.right = right;
                    }else{
                        poll.right = new TreeNode(val);
                    }
                }
                break;
            }
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (null!=poll.left) {
                    queue.offer( poll.left );
                }
                if (null!=poll.right) {
                    queue.offer( poll.right );
                }
            }
            level++;
        }
        return root;
    }
}
