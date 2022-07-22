package com.wp;
import cn.hutool.core.thread.SemaphoreRunnable;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * @author: wp
 * @Title: task_814  814. 二叉树剪枝
 * @Description: https://leetcode.cn/problems/binary-tree-pruning/
 * @date 2022/7/21 9:42
 */
public class task_814 {
    public static void main( String[] args ) {
        TreeNode node = new TreeNode( 0, null, new TreeNode(0, new TreeNode(1, null, new TreeNode(1)),new TreeNode(1, null, new TreeNode(1))) );
        node = pruneTree( node );
        System.out.println( node );
    }
    public static TreeNode pruneTree(TreeNode root) {
        Map<TreeNode,TreeNode> parent = new HashMap<>();
        Set<TreeNode> hasOneSet = new HashSet<>();
        dfs(root, parent, hasOneSet);
        if (!hasOneSet.contains(root)) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                if (null!=left && hasOneSet.contains( left )) {
                    queue.offer( left );
                }else{
                    node.left = null;
                }
                TreeNode right = node.right;
                if (null!=right && hasOneSet.contains( right )) {
                    queue.offer( right );
                }else{
                    node.right = null;
                }
            }
        }
        return root;
    }

    private static void dfs( TreeNode node, Map<TreeNode, TreeNode> parent, Set<TreeNode> hasOneSet ) {
        if (node.val==1) {
            hasOneSet.add( node );
            TreeNode tmp = node;
            TreeNode p;
            while (null!=(p = parent.get( tmp ))) {
                hasOneSet.add( p );
                tmp = p;
            }
        }
        if (null!=node.left) {
            parent.put( node.left, node );
            dfs(node.left, parent, hasOneSet);
        }
        if (null!=node.right) {
            parent.put( node.right, node );
            dfs( node.right, parent, hasOneSet );
        }

    }

    private static boolean failure( TreeNode root ) {
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode,TreeNode> leftParent = new HashMap<>();
        Map<TreeNode,TreeNode> rightParent = new HashMap<>();
        queue.offer( root );
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                boolean hasOne = false;
                if (node.val==1) {
                    hasOne = true;
                }
                TreeNode left = node.left;
                if (left!=null && !hasOne && left.val==1) {
                    hasOne = true;
                }
                TreeNode right = node.right;
                if (null!=right && !hasOne && right.val == 1) {
                    hasOne = true;
                }

                if (hasOne) {
                    if (null!=left) {
                        leftParent.put( left, node );
                        queue.offer( left );
                    }
                    if (null!=right) {
                        rightParent.put( right, node );
                        queue.offer( right );
                    }
                }else{
                    if (node== root) {
                        return true;
                    }
                    if (leftParent.containsKey(node)) {
                        leftParent.get( node ).left = null;
                    }
                    if (rightParent.containsKey(node)) {
                        rightParent.get( node ).right = null;
                    }
                }
            }
        }
        return false;
    }
}
