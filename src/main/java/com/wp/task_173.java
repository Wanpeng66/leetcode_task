package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_173  173. 二叉搜索树迭代器
 * @Description: https://leetcode-cn.com/problems/binary-search-tree-iterator/
 * @date 2022/2/14 17:42
 */
public class task_173 {

    class BSTIterator {
        int index = -1;
        Integer[] array;

        public BSTIterator(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            inOrder(root, list);
            array = list.toArray( new Integer[0] );
        }

        private void inOrder( TreeNode root, List<Integer> list ) {
            if (null==root) {
                return;
            }
            inOrder( root.left, list );
            list.add( root.val );
            inOrder( root.right, list );
        }

        public int next() {
            index++;
            return array[index];
        }

        public boolean hasNext() {
            return index+1<array.length;
        }
    }
}
