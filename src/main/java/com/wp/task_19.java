package com.wp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: wp
 * @Title: task_19 19. 删除链表的倒数第 N 个结点
 * @Description: https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * @date 2022/3/22 11:18
 */
public class task_19 {

    public static void main( String[] args ) {
        ListNode head = new ListNode(1,new ListNode(2));
        head = removeNthFromEnd( head, 2 );
        System.out.println( head.toString() );
        int i = new Random( 5000 ).nextInt( 5000 );
        System.out.println( i );
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();
        int index = 0;
        while (null!=head) {
            list.add( index++,head );
            head = head.next;
        }
        n = list.size()-n;
        if (list.size()==1) {
            return null;
        }
        if (n==0) {
            return list.get( n+1 );
        }
        ListNode prefix = list.get( n - 1 );
        ListNode next = null;
        if (list.size()-1>n) {
            next = list.get( n+1 );
        }
        prefix.next = next;
        return  list.get( 0 );
    }
}
