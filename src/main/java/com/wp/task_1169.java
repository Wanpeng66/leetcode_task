package com.wp;

public class task_1169 {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))));
        ListNode list2 = new ListNode(100, new ListNode(101, new ListNode(102, new ListNode(103))));
        ListNode node = mergeInBetween(list1, 3, 5, list2);
        System.out.println(node);
    }

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode preNode = list1, tmpNode = list1;
        int index = 0;
        while (null!=tmpNode) {
            if (index==a) {
                preNode.next = list2;
            }
            preNode = tmpNode;
            tmpNode = tmpNode.next;
            if (index==b) {
                preNode.next = null;
                break;
            }
            index++;
        }
        while (list2.next!=null) {
            list2 = list2.next;
        }
        list2.next = tmpNode;
        return list1;
    }
}
