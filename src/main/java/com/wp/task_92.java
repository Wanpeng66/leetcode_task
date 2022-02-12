package com.wp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: wp
 * @Title: task_92 92. 反转链表 II
 * @Description: https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * @date 2022/1/24 14:51
 */
public class task_92 {

    public static void main( String[] args ) {
        ListNode head = new ListNode( 1, new ListNode( 2, new ListNode( 3, new ListNode( 4, new ListNode( 5 ) ) ) ) );
        ListNode node = reverseBetween( head, 1, 5 );
        print( node );
    }

    public static void print( ListNode head){
        while(null!=head){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (null==head || left==right) return head;
        List<ListNode> list = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();
        for(int i=1 ; ; i++){
            if (i<left || i>right) {
                list.add( i-1, head );
            }else{
                list.add( i-1, null );
                stack.addElement( head );
            }
            head = head.next;
            if (null==head) break;
        }
        for(int i = left;i <= right;i++){
            list.add( i-1, stack.pop() );
        }
        ListNode nHead = null;
        ListNode tmp = null;
        for (int i = 0; i < list.size(); i++) {
            ListNode node = list.get( i );
            if (null==node) continue;
            node.next = null;
            if(i==0) nHead = node;
            if (i>0) {
                tmp.next = node;
            }
            tmp = node;
        }
        return nHead;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
