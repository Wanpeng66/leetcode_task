package com.wp;

public class task_2331 {
    public boolean evaluateTree(TreeNode root) {
        if (null==root.left && null== root.right) {
            return root.val != 0;
        }
        int val = root.val;
        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);
        return val==2 ? left||right : left&&right;
    }
}
