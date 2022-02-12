package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_99  99. 恢复二叉搜索树
 * @Description: https://leetcode-cn.com/problems/recover-binary-search-tree/
 * @date 2022/2/11 14:01
 */
public class task_99 {

    public static void main( String[] args ) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        recoverTree( root );
        System.out.println( root.val );
    }

    public static void recoverTree(TreeNode root) {
        if (null==root) {
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        inOrder(root,list);
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            flag = false;
            for (int j = 0; j < list.size()-i-1; j++) {
                TreeNode node = list.get( j );
                TreeNode next = list.get( j + 1 );
                if (node.val>next.val) {
                    int val = node.val;
                    node.val = next.val;
                    next.val = val;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    private static void inOrder( TreeNode node, List<TreeNode> list ) {
        if (null==node) {
            return;
        }
        inOrder( node.left, list );
        list.add( node );
        inOrder( node.right, list );
    }
}
