package com.wp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: wp
 * @Title: task_147 147. 对链表进行插入排序
 * @Description: https://leetcode-cn.com/problems/insertion-sort-list/
 * @date 2022/1/30 14:31
 */
public class task_147 {

    public static void main( String[] args ) {
        ListNode head = new ListNode( 4,new ListNode( 2, new ListNode( 1, new ListNode( 3 ) ) ) );
        head = insertionSortList( head );
        System.out.println( head.val );
    }

    public static ListNode insertionSortList(ListNode head) {
        if (null==head|| null==head.next) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        while (null!=head) {
            list.add( head );
            head = head.next;
        }
        list.sort( ( o1, o2 ) -> o1.val-o2.val );
        /*for (int i = 1; i < list.size(); i++) {
            ListNode node = list.get( i );
            int offset = -1;
            for (int j = i-1; j >= 0; j--) {
                if (list.get( j ).val>node.val) {
                    list.set( j+1, list.get( j ) );
                    offset = j;
                }else{
                    break;
                }
            }
            if (-1!=offset) {
                list.set( offset,node );
            }

        }*/
        ListNode tmp = null;
        for (int i = 0; i < list.size(); i++) {
            ListNode node = list.get( i );
            if (null==tmp) {
                tmp = node;
                head = node;
            }else{
                tmp.next = node;
                tmp = node;
            }
        }
        tmp.next = null;
        return head;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
