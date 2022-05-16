package com.wp;

import org.apache.poi.ss.formula.functions.T;

import java.util.*;

/**
 * @author: wp
 * @Title: task_449  449. 序列化和反序列化二叉搜索树
 * @Description: https://leetcode.cn/problems/serialize-and-deserialize-bst/
 * @date 2022/5/11 9:39
 */
public class task_449 {
    public static void main( String[] args ) {
        Integer[] array = {41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23};
        TreeNode node = TreeNode.array2Tree( array );
        Codec tmp = new Codec();
        String serialize = tmp.serialize( node );
        System.out.println( serialize );
        TreeNode root = tmp.deserialize( serialize );
        System.out.println( root );
    }
    static class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (null==root) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            preOrder(root, sb);
            return sb.deleteCharAt( sb.length()-1 ).toString();
        }

        private void preOrder( TreeNode root, StringBuilder sb ) {
            if (null==root) {
                return;
            }
            sb.append( root.val ).append( "," );
            preOrder(root.left, sb);
            preOrder(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if ("".equalsIgnoreCase( data )||null==data) {
                return null;
            }
            int[] preOrder = Arrays.stream( data.split( "," ) ).mapToInt( Integer :: parseInt ).toArray();
            int[] middle = Arrays.copyOf( preOrder, preOrder.length );
            Arrays.sort( middle );
            return dfs(middle, preOrder, 0, middle.length-1, 0);
        }

        private TreeNode dfs( int[] middle, int[] preOrder, int mLeft, int mRight, int pRoot ) {
            if (mLeft>mRight) {
                return null;
            }
            if (mLeft==mRight) {
                return new TreeNode(middle[mLeft]);
            }
            int root = preOrder[pRoot];
            int mRoot = 0;
            for (int i = mLeft; i <= mRight; i++) {
                if (middle[i]==root) {
                    mRoot = i;
                    break;
                }
            }
            TreeNode node = new TreeNode(root);
            node.left = dfs(middle, preOrder, mLeft, mRoot-1, pRoot+1);
            int leftNum = mRoot-mLeft;
            node.right = dfs(middle, preOrder, mRoot+1, mRight, pRoot+leftNum+1);
            return node;
        }
    }
}
