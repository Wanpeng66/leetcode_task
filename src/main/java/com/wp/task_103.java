package com.wp;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_103  103. 二叉树的锯齿形层序遍历
 * @Description: https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * @date 2022/2/14 15:07
 */
public class task_103 {

    public static void main( String[] args ) {
        Integer[] array = {3,9,20,null,null,15,7};
        TreeNode node = TreeNode.array2Tree( array );
        List<List<Integer>> lists = zigzagLevelOrder( node );
        System.out.println( lists );
    }

    public static List<List<Integer>> zigzagLevelOrder( TreeNode root) {
        if(null==root){
            return new ArrayList<>();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> lists = new ArrayList<>();
        deque.offer( root );
        for(int i=0;!deque.isEmpty();i++){
            int size = deque.size();
            TreeNode[] array = new TreeNode[size];
            for (int j = 0; j < size; j++) {
                array[j] = deque.remove();
            }
            List<Integer> list = new ArrayList<>();
            lists.add( list );
            for (int k = 0; k < array.length; k++) {
                TreeNode node = array[k];
                if (null!=node) {
                    deque.offer( node.left );
                    deque.offer( node.right );
                }
            }
            for (int k = 0; k < array.length; k++) {
                int offset = k;
                if (i%2!=0) {
                    offset = array.length-1-k;
                }
                TreeNode node = array[offset];
                if (null!=node) {
                    list.add( node.val );
                }
            }
            if (list.isEmpty()) {
                lists.remove( list );
            }
        }
        return lists;
    }
}
