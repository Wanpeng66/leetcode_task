package com.wp;

/**
 * @author: wp
 * @Title: task_0406  面试题 04.06. 后继者
 * @Description: https://leetcode.cn/problems/successor-lcci/
 * @date 2022/5/16 13:56
 */
public class task_0406 {
    public static void main( String[] args ) {
        TreeNode treeNode = inorderSuccessor( TreeNode.array2Tree( new Integer[]{5,3,6,2,4,null,null,1} ), new TreeNode( 1 ) );
        System.out.println( treeNode );
    }
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode[] tmp = new TreeNode[1];
        boolean[] flag = new boolean[]{false};
        inorder(root, p, tmp, flag );
        return flag[0]?tmp[0]:null;
    }

    private static void inorder( TreeNode root, TreeNode p, TreeNode[] tmp, boolean[] flag ) {
        if (null!=root && !flag[0]) {
            inorder( root.left, p, tmp, flag );
            if (flag[0]) {
                return;
            }
            if (null!=tmp[0] && tmp[0].val==p.val) {
                tmp[0] = root;
                flag[0] = true;
                return;
            }

            tmp[0] = root;
            inorder( root.right,p, tmp, flag );
        }
    }
}
