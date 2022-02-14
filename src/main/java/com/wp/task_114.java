package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_114  114. 二叉树展开为链表
 * @Description: https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * @date 2022/2/14 10:12
 */
public class task_114 {

    public static void main( String[] args ) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        flatten( root );
        System.out.println( root.val );
    }

    public static void flatten(TreeNode root) {
        if (null==root) {
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        preOrder(root, list);
        for (int i = 0; i < list.size()-1; i++) {
            TreeNode node = list.get( i );
            node.left = null;
            node.right = list.get(i+1);
        }
        TreeNode node = list.get( list.size() - 1 );
        node.left = null;
        node.right = null;
    }

    private static void preOrder( TreeNode root, List<TreeNode> list ) {
        if (null==root) {
            return;
        }
        list.add( root );
        preOrder( root.left, list );
        preOrder( root.right, list );
    }
}
