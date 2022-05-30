package com.wp;

/**
 * @author: wp
 * @Title: task_1022 1022. 从根到叶的二进制数之和
 * @Description: https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers/
 * @date 2022/5/30 15:14
 */
public class task_1022 {

    public static void main( String[] args ) {
        System.out.println( sumRootToLeaf( TreeNode.array2Tree( new Integer[]{ 1,0,1,0,1,0,1 } ) ) );
    }
    public static int sumRootToLeaf(TreeNode root) {
        int[] sum = {0};
        dfs(root, sum, new StringBuilder());
        return sum[0];
    }

    private static void dfs( TreeNode node, int[] sum, StringBuilder buffer ) {
        buffer.append( node.val );
        if (null==node.left&&null==node.right) {
            sum[0]+=Integer.parseInt( buffer.toString(),2 );
            return;
        }
        if (null!=node.left) {
            dfs( node.left, sum, buffer );
            buffer.deleteCharAt( buffer.length()-1 );
        }
        if (null!=node.right) {
            dfs( node.right, sum, buffer );
            buffer.deleteCharAt( buffer.length()-1 );
        }
    }
}
