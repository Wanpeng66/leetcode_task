package com.wp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_919  919. 完全二叉树插入器
 * @Description: https://leetcode.cn/problems/complete-binary-tree-inserter/
 * @date 2022/7/25 10:00
 */
public class task_919 {
    public static void main( String[] args ) {
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        CBTInserter inserter = new CBTInserter( root );
        System.out.println( inserter.insert( 3 ) );
        System.out.println( inserter.insert( 4 ) );
        TreeNode node = inserter.get_root();
        System.out.println( node.val );

    }
    static class CBTInserter {
        List<TreeNode> array;
        public CBTInserter(TreeNode root) {
            array = new ArrayList<>();
            int offset = 0;
            array.add( offset++,new TreeNode(-1) );
            array.add( offset, root );
            while (offset<array.size()) {
                TreeNode node = array.get( offset++ );
                if (null!=node.left) {
                    array.add( node.left );
                }
                if (null!=node.right) {
                    array.add( node.right );
                }
            }
        }

        public int insert(int val) {
            TreeNode node = new TreeNode( val );
            array.add( node );
            int size = array.size() - 1;
            TreeNode parent = array.get( size / 2 );
            if (size%2==0) {
                parent.left = node;
            }else{
                parent.right = node;
            }
            return parent.val;
        }

        public TreeNode get_root() {
            return array.get( 1 );
        }
    }
}
