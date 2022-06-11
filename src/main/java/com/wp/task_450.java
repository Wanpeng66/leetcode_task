package com.wp;


/**
 * @author: wp
 * @Title: task_450  450. 删除二叉搜索树中的节点
 * @Description: https://leetcode.cn/problems/delete-node-in-a-bst/
 * @date 2022/6/2 15:39
 */
public class task_450 {
    public static void main( String[] args ) {
        TreeNode node = TreeNode.array2Tree( new Integer[]{5, 3, 6, 2, 4, null, 7} );
        TreeNode treeNode = deleteNode( node, 3 );
        System.out.println( treeNode );
    }
    public static TreeNode deleteNode(TreeNode root, int key) {
        TreeNode node = root,node_p = null;
        while (null!=node) {
            if (node.val==key) {
                break;
            }else if (node.val>key) {
                node_p = node;
                node = node.left;
            } else {
                node_p = node;
                node = node.right;
            }
        }
        if (null==node) {
            return root;
        }
        TreeNode tmp;
        if (null!=node.left&&null==node.right) {
            tmp = node.left;
        } else if (null==node.left&&null!=node.right) {
            tmp = node.right;
        } else if (null != node.left) {
            TreeNode right = node.right;
            while (right.left!=null) {
                right = right.left;
            }
            right.left = node.left;
            tmp = node.right;
        }else{
            tmp = null;
        }
        if (node==root) {
            return tmp;
        }else{
            if (node_p.left==node) {
                node_p.left = tmp;
            } else if (node_p.right==node) {
                node_p.right = tmp;
            }
            return root;
        }
    }
}
