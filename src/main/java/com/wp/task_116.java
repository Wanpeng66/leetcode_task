package com.wp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: wp
 * @Title: task_116  116. 填充每个节点的下一个右侧节点指针
 * @Description: https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 * @date 2022/2/18 17:30
 */
public class task_116 {

    public Node connect(Node root) {
        if (null==root) {
            return null;
        }
        Deque<Node> deque = new LinkedList();
        deque.offer( root );
        while (!deque.isEmpty()) {
            int size = deque.size();
            Node[] array = new Node[size];
            for (int i = 0; i < size; i++) {
                Node node = deque.poll();
                array[i] = node;
                if (null!=node.left) {
                    deque.offer( node.left );
                }
                if (null!=node.right) {
                    deque.offer( node.right );
                }
            }
            Node right = null;
            for (int i = array.length - 1; i >= 0; i--) {
                array[i].next = right;
                right = array[i];
            }
        }
        return root;
    }
}
