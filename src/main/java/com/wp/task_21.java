package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_21 21. 合并两个有序链表
 * @Description: https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * @date 2022/1/24 16:44
 */
public class task_21 {

    public static void main( String[] args ) {
        ListNode list1 = new ListNode( 1, new ListNode( 2, new ListNode( 4 ) ) );
        ListNode list2 = new ListNode( 1, new ListNode( 3, new ListNode( 4) ) );
        ListNode node = mergeTwoLists( list1, list2 );
        print(node);
    }

    public static void print( ListNode head){
        while(null!=head){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (null==list1 && null==list2) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
       for(int i=0;;i++) {
           if (null==list1 && null!=list2) {
               list.add( list2 );
               list2 = list2.next;
           } else if (null==list2 && null!=list1) {
               list.add( list1 );
               list1 = list1.next;
           }else{
               if (list1.val>list2.val) {
                   list.add(list2 );
                   list2 = list2.next;
               }else if(list2.val>list1.val){
                   list.add( list1 );
                   list1 = list1.next;
               }else{
                   list.add( list1 );
                   list.add( list2 );
                   list1 = list1.next;
                   list2 = list2.next;
               }
           }
           if (null==list1 && null==list2) {
               break;
           }
        }
        ListNode nHead = null;
        ListNode tmp = null;
        for (int i = 0; i < list.size(); i++) {
            ListNode node = list.get( i );
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
