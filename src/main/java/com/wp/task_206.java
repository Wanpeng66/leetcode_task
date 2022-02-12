package com.wp;

/**
 * @author: wp
 * @Title: task_206 反转单向链表
 * @Description: https://leetcode-cn.com/problems/reverse-linked-list/
 * @date 2022/1/24 14:25
 */
public class task_206 {

    public static void main( String[] args ) {
        ListNode head = new ListNode( 1, new ListNode( 2, new ListNode( 3, new ListNode( 4, new ListNode( 5 ) ) ) ) );
        ListNode node = reverseList( head );
        print( node );
    }
    public static ListNode reverseList(ListNode head) {
        ListNode tmp = null;
        while (null!=head) {
            ListNode next = head.next;
            head.next = tmp;
            tmp = head;
            head = next;
        }
        return tmp;
    }

    public static void print(ListNode head){
        while(null!=head){
            System.out.println(head.val);
            head = head.next;
        }
    }
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

