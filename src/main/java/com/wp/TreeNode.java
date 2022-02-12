package com.wp;

/**
 * @author: wp
 * @Title: TreeNode
 * @Description:
 * @date 2022/2/10 17:09
 */
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}
