package com.wp;

/**
 * @author: wp
 * @Title: task_234
 * @Description:
 * @date 2022/1/28 13:47
 */
public class task_234 {

    public static void main( String[] args ) {
        ListNode one = new ListNode(1,new ListNode( 2,new ListNode( 3,new ListNode( 4, new ListNode( 2, new ListNode( 1 ) ) ) ) ));
        System.out.println( isPalindrome2( one ) );
    }

    public static boolean isPalindrome2(ListNode head){
        if (null==head) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        ListNode prefix = null;
        while (null!=head) {
            sb.append( head.val );
            ListNode next = head.next;
            head.next = prefix;
            prefix = head;
            head = next;
        }
        String tmp = sb.toString();
        sb.setLength( 0 );
        while (null!=prefix) {
            sb.append( prefix.val );
            prefix = prefix.next;
        }
        return tmp.equals( sb.toString() );
    }

    public static boolean isPalindrome(ListNode head) {
        if (null==head) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode prefix = null;
        ListNode suffix = null;
        int i = 1;
        while (null!=fast) {
            if (null==fast.next) {
                //奇数
                i = 1;
                break;
            } else if (null==fast.next.next) {
                //偶数
                i = 2;
                break;
            }else{
                fast = fast.next.next;
            }
            ListNode tmp = slow.next;
            slow.next = prefix;
            prefix = slow;
            slow = tmp;
        }
        if (i==2) {
            suffix = slow.next;
            slow.next = prefix;
            prefix = slow;
        }else{
            suffix = slow.next;
        }
        while (null!=suffix) {
            if (suffix.val!= prefix.val) {
                return false;
            }
            suffix = suffix.next;
            prefix = prefix.next;
        }
        return true;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
