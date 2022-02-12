package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_94  94. 二叉树的中序遍历
 * @Description: https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * @date 2022/2/10 17:10
 */
public class task_94 {

    public List<Integer> inorderTraversal( TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder( TreeNode node, List<Integer> list ) {
        if (null==node) {
            return;
        }
        inorder( node.left,list );
        list.add( node.val );
        inorder( node.right,list );
    }
}
