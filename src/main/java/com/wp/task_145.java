package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_145 145. 二叉树的后序遍历
 * @Description: https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * @date 2022/2/10 17:18
 */
public class task_145 {

    public List<Integer> postorderTraversal( TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root,list);
        return list;
    }

    private void postorder( TreeNode node, List<Integer> list ) {
        if (null==node) {
            return;
        }
        postorder( node.left,list );
        postorder( node.right,list );
        list.add( node.val );
    }
}
