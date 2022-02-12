package com.wp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: wp
 * @Title: task_109  109. 有序链表转换二叉搜索树
 * @Description: https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
 * @date 2022/2/11 15:39
 */
public class task_109 {

    public static void main( String[] args ) {
        ListNode node = new ListNode(-10,new ListNode( -3, new ListNode( 0,new ListNode( 5,new ListNode( 9 ) ) ) ));
        TreeNode root = sortedListToBST( node );
        System.out.println( root.val );
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (null==head) {
            return null;
        }
        return toBst(head);
    }

    private static TreeNode toBst( ListNode head ) {
        if (null==head) {
            return null;
        }
        if (null==head.next) {
            return new TreeNode(head.val);
        }
        ListNode slow = head,fast = head.next;
        ListNode pre = null;
        while (fast!=null) {
            pre = slow;
            slow = slow.next;
            if (null==fast.next) {
                fast = fast.next;
            }else{
                fast = fast.next.next;
            }
        }
        TreeNode root = new TreeNode(slow.val);
        ListNode next = slow.next;
        if (null!=pre) {
            pre.next = null;
        }
        if (null!=next) {
            slow.next = null;
        }
        root.left = toBst( head );
        root.right = toBst( next );
        return root;
    }

    private TreeNode method1( ListNode head ) {
        List<Integer> nums = new ArrayList<>();
        while (null!= head) {
            nums.add( head.val );
            head = head.next;
        }
        return toBst(nums,0, nums.size()-1);
    }

    private TreeNode toBst( List<Integer> nums, int left, int right ) {
        if (left>right) {
            return null;
        }
        int middle = left + (right - left)/2;
        TreeNode root = new TreeNode(nums.get( middle ));
        root.left = toBst( nums,left,middle-1 );
        root.right = toBst( nums,middle+1,right );
        return root;
    }
}
