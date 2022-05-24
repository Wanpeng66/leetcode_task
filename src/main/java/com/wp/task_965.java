package com.wp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author: wp
 * @Title: task_965  965. 单值二叉树
 * @Description: https://leetcode.cn/problems/univalued-binary-tree/
 * @date 2022/5/24 16:38
 */
public class task_965 {
    public static void main( String[] args ) {
        TreeNode root = TreeNode.array2Tree( new Integer[]{ 2,2,2,5,2 } );
        System.out.println( isUnivalTree( root ) );
    }
    public static boolean isUnivalTree(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (set.isEmpty()) {
                    set.add( poll.val );
                } else if (!set.contains( poll.val )) {
                    return false;
                }
                if (null!=poll.left) {
                    queue.offer( poll.left );
                }
                if (null!=poll.right) {
                    queue.offer( poll.right );
                }
            }
        }
        return true;
    }
}
