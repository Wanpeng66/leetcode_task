package com.wp;

/**
 * @author: wp
 * @Title: task_2 2. 两数相加
 * @Description: https://leetcode-cn.com/problems/add-two-numbers/
 * @date 2022/3/1 14:04
 */
public class task_2 {

    public static void main( String[] args ) {
        ListNode l1 = new ListNode(2,new ListNode( 4, new ListNode( 3 ) ));
        ListNode l2 = new ListNode(5, new ListNode( 6, new ListNode( 4 ) ));
        ListNode node = addTwoNumbers( l1, l2 );
        System.out.println( node );
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = l1;
        ListNode node = null;
        int add = 0;
        while (null!=l1 && null!=l2) {
            int sum = l1.val + l2.val + add;
            add = 0;
            if (sum>9) {
                add = 1;
                l1.val = sum-10;
            }else{
                l1.val = sum;
            }
            node = l1;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1!=null) {
            int sum = l1.val + add;
            add = 0;
            if (sum>9) {
                add = 1;
                l1.val = sum-10;
            }else{
                l1.val = sum;
            }
            node = l1;
            l1 = l1.next;
        }
        while (l2!=null) {
            int sum = l2.val + add;
            add = 0;
            l1 = new ListNode();
            if (sum>9) {
                add = 1;
                l1.val = sum-10;
            }else{
                l1.val = sum;
            }
            l2 = l2.next;
            node.next = l1;
            node = l1;
        }
        if (add!=0) {
            node.next = new ListNode( add );
        }
        return head;
    }
}
