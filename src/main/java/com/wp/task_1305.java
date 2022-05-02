package com.wp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: wp
 * @Title: task_1305  1305. 两棵二叉搜索树中的所有元素
 * @Description: https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
 * @date 2022/5/1 11:18
 */
public class task_1305 {
    public List<Integer> getAllElements( TreeNode root1, TreeNode root2) {
        ArrayList<Integer> one = new ArrayList<>();
        dfs(root1, one );
        ArrayList<Integer> two = new ArrayList<>();
        dfs(root2, two );
        one.addAll( two );
        one.sort( Comparator.naturalOrder() );
        return one;
    }

    private void dfs( TreeNode root, ArrayList<Integer> list ) {
        if (null==root) {
            return;
        }
        dfs(root.left, list);
        list.add( root.val );
        dfs(root.right, list);
    }
}
