package com.wp;

/**
 * @author: wp
 * @Title: task_108  108. 将有序数组转换为二叉搜索树
 * @Description: https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * @date 2022/2/11 15:32
 */
public class task_108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return toBst(nums,0,nums.length-1);
    }

    private TreeNode toBst( int[] nums, int left, int right ) {
        if (left>right) {
            return null;
        }
        int middle = left + (right - left)/2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = toBst( nums,left, middle-1 );
        root.right = toBst( nums,middle+1,right );
        return root;
    }
}
