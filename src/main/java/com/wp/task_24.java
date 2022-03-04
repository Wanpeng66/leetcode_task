package com.wp;


/**
 * @author: wp
 * @Title: task_24  24. 两两交换链表中的节点
 * @Description: https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * @date 2022/3/4 14:33
 */
public class task_24 {
    public static void main( String[] args ) {
        ListNode head = new ListNode( 1, new ListNode( 2, new ListNode( 3, new ListNode( 4 ) ) ) );
        ListNode node = swapPairs( head );
        System.out.println( node );
    }

    public static ListNode swapPairs(ListNode head) {
        int num = 0;
        int count = 0;
        ListNode node = head;
        ListNode pre = null;
        ListNode prepre = null;
        ListNode next = null;
        while (null!=head) {
            if (num==1) {
                if (count++==0) {
                    node = head;
                }
                next = head.next;
                pre.next = next;
                head.next = pre;
                if (null!=prepre) {
                    prepre.next = head;
                }
                prepre = pre;
                head = next;
                num = 0;
            }else{
                num++;
                pre = head;
                head = head.next;
            }
        }
        return node;
    }
}
