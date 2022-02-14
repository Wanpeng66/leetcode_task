package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_110   110. 平衡二叉树
 * @Description: https://leetcode-cn.com/problems/balanced-binary-tree/
 * @date 2022/2/14 10:35
 */
public class task_110 {

    public static void main( String[] args ) {
        //String[] array = {"1","2","2","3","3","3","3","4","4","4","4","4","4","null","null","5","5"};
        //TreeNode root = TreeNode.array2Tree( array );
        //System.out.println( isBalanced( root ) );
    }

    public static boolean isBalanced(TreeNode root) {
        if(null==root){
            return true;
        }
        return Math.abs( hegiht( root.left )-hegiht( root.right ) )<2 && isBalanced( root.left ) && isBalanced( root.right );
    }

    private static int hegiht( TreeNode root ) {
        if (null==root) {
            return 0;
        }
        return Math.max( hegiht( root.left ), hegiht( root.right ) )+1;
    }


}
