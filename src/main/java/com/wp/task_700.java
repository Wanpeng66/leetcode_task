package com.wp;

/**
 * @author: wp
 * @Title: task_700  700. 二叉搜索树中的搜索
 * @Description: https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 * @date 2022/2/14 16:29
 */
public class task_700 {

    public TreeNode searchBST(TreeNode root, int val) {
        while (null!=root) {
            if (root.val==val) {
                return root;
            } else if (root.val>val) {
                root = root.left;
            }else{
                root = root.right;
            }
        }
        return null;
    }
}
