package com.wp;

/**
 * @author: wp
 * @Title: task_230  230. 二叉搜索树中第K小的元素
 * @Description:  https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 * @date 2022/2/14 18:02
 */
public class task_230 {

    public static void main( String[] args ) {
        Integer[] array = {5,3,6,2,4,null,null,1};
        TreeNode node = TreeNode.array2Tree( array );
        System.out.println( kthSmallest( node, 3 ) );
    }

    public static int kthSmallest(TreeNode root, int k) {
        Integer[] res = {null};
        inOrder(root, res, k, new Integer[]{1});
        return res[0];
    }

    private static void inOrder( TreeNode root, Integer[] res, int k, Integer[] i ) {
        if (null==root) {
            return;
        }
        inOrder( root.left, res, k , i );
        if (i[0]++ == k) {
            if (null==res[0]) {
                res[0] = root.val;
            }
            return;
        }
        inOrder( root.right,res, k, i );
    }
}
