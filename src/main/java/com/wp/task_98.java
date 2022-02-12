package com.wp;

/**
 * @author: wp
 * @Title: task_98  98. 验证二叉搜索树
 * @Description: https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @date 2022/2/11 11:47
 */
public class task_98 {

    public static void main( String[] args ) {
        TreeNode root = new TreeNode(2147483647);
        //root.left = new TreeNode(1);
        //root.right = new TreeNode(4);
        //root.right.left = new TreeNode(3);
        //root.right.right = new TreeNode(6);
        System.out.println( isValidBST( root ) );
    }


    public static boolean isValidBST(TreeNode root) {
        if (null==root) {
            return true;
        }
        int val = root.val;
        long min = 0,max = 0;
        TreeNode tmp = root.right;
        while (null!=tmp && null!=tmp.left) {
            tmp = tmp.left;
        }
        min = null!=tmp?tmp.val:(long)val+1;
        tmp = root.left;
        while (null!=tmp && null!=tmp.right) {
            tmp = tmp.right;
        }
        max = null!=tmp?tmp.val:(long)val-1;
        if (!(min>val && max<val)) {
            return false;
        }
        if (((null!=root.left && null!=root.right) && !(root.left.val<val && root.right.val>val))) {
            return false;
        }
        return isValidBST( root.left ) && isValidBST( root.right );
    }
}
