package com.wp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_513  513. 找树左下角的值
 * @Description: https://leetcode.cn/problems/find-bottom-left-tree-value/
 * @date 2022/6/22 13:27
 */
public class task_513 {
    public static void main( String[] args ) {
        TreeNode root = TreeNode.array2Tree( new Integer[]{1, 2, 3, 4, null, 5, 6, null, null, 7} );
        System.out.println( findBottomLeftValue( root ) );
    }
    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        int value = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            value = queue.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (null!=poll.left) {
                    queue.offer( poll.left );
                }
                if (null!=poll.right) {
                    queue.offer( poll.right );
                }
            }
        }
        return value;
    }
}
