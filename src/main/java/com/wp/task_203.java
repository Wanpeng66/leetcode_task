package com.wp;

import java.util.List;

/**
 * @author: wp
 * @Title: task_203  203. 移除链表元素
 * @Description: https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @date 2022/1/28 11:44
 */
public class task_203 {

    public static void main( String[] args ) {
        ListNode one = new ListNode(1,new ListNode( 2,new ListNode( 3,new ListNode( 3, new ListNode( 5 ) ) ) ));
        ListNode listNode = removeElements2( one, 3 );
        System.out.println( listNode.val );
    }

    public static ListNode removeElements2(ListNode head, int val) {
        ListNode header = new ListNode(Integer.MAX_VALUE);
        header.next = head;
        ListNode cur = header;
        while (null!=cur.next) {
            if (cur.next.val==val) {
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return header.next;
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (null==head) {
            return null;
        }
        ListNode tmp = null;
        ListNode prefix = null;
        while (null!=head) {
            if (null==tmp&&head.val!=val) {
                tmp = head;
            }
            if (head.val!=val) {
                prefix = head;
            }else if(null!=prefix){
                prefix.next = head.next;
            }
            head = head.next;
        }
        return tmp;
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
