package com.wp;

/**
 * @author: wp
 * @Title: task_101 101. 对称二叉树
 * @Description: https://leetcode-cn.com/problems/symmetric-tree/
 * @date 2022/2/10 17:22
 */
public class task_101 {

    public boolean isSymmetric(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        //将树拆分为两个树 同时递归 比较 左树的左节点是否等于右树的右节点&&左树的右节点是否等于右树的左节点
        return isSymmetric(left, right);
    }

    private boolean isSymmetric( TreeNode left, TreeNode right ) {
        if (null==left && null==right) {
            return true;
        }
        if (null==left || null == right) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric( left.left,right.right )&&isSymmetric( left.right,right.left ) ;
    }
}
