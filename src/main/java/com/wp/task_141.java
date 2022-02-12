package com.wp;

/**
 * @author: wp
 * @Title: task_141 141. 环形链表
 * @Description: https://leetcode-cn.com/problems/linked-list-cycle/
 * @date 2022/1/24 15:40
 */
public class task_141 {

    public static void main( String[] args ) {
        ListNode tail = new ListNode( 5 );
        ListNode head = new ListNode( 1, new ListNode( 2, new ListNode( 3, new ListNode( 4, tail ) ) ) );
        //tail.next = head;
        System.out.println( hasCycle( head ) );
    }

    public static boolean hasCycle(ListNode head) {
        if (null==head || null==head.next) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow!=fast) {
            if (null==fast||null==fast.next) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
