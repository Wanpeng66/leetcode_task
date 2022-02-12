package com.wp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: task_148  148. 排序链表
 * @Description: https://leetcode-cn.com/problems/sort-list/
 * @date 2022/2/1 13:29
 */
public class task_148 {

    public static void main( String[] args ) {
        ListNode head = new ListNode( 4, new ListNode( 2, new ListNode( 1, new ListNode( 3 ) ) ) );
        sortList( head );
        System.out.println( head );
    }

    public static ListNode sortList(ListNode head) {
        if (null==head || null==head.next) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        while (null!=head) {
            list.add( head );
            head = head.next;
        }
        split(list, 0, list.size()-1);
        //sort(list, 0, list.size()-1);
        //list.sort( ( o1, o2 ) -> o1.val-o2.val );
        ListNode tmp = null;
        for (ListNode node : list) {
            node.next = null;
            if (null==tmp) {
                head = node;
                tmp = node;
            }else{
                tmp.next = node;
                tmp = node;
            }
        }
        return head;
    }

    private static void split( List<ListNode> list, int p, int r ) {
        if (p>=r) {
            return;
        }
        int q = (p+r)/2;
        split( list,p,q );
        split( list,q+1,r );
        merge(list, list.stream().skip( p ).limit( q-p+1 ).collect( Collectors.toList()), list.stream().skip( q+1 ).limit( r-q ).collect( Collectors.toList()), p );
    }

    private static void merge( List<ListNode> list, List<ListNode> one, List<ListNode> two, int p ) {
        int i1 = 0,i2 = 0;
        while (i1<one.size()&&i2< two.size()) {
            if (one.get( i1 ).val<=two.get( i2 ).val) {
                list.set( p++,one.get( i1++ ) );
            }else{
                list.set( p++,two.get( i2++ ) );
            }
        }
        while(i1<one.size()){
            list.set( p++,one.get( i1++ ) );
        }
        while (i2< two.size()) {
            list.set( p++,two.get( i2++ ) );
        }
    }

    private static void sort( List<ListNode> list, int p, int r ) {
        if (p>=r) {
            return;
        }
        int q = partion(list, p, r);
        sort( list, p,q-1 );
        sort( list, q+1, r );
    }

    private static int partion( List<ListNode> list, int p, int r ) {
        int index = p;
        ListNode node = list.get( r );
        for(;p<r;p++){
            if (list.get( p ).val<node.val) {
                ListNode tmp = list.get( p );
                list.set( p,list.get( index ) );
                list.set( index,tmp );
                index++;
            }
        }
        list.set( r,list.get( index ) );
        list.set( index,node );
        return index;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
