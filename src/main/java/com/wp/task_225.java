package com.wp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: wp
 * @Title: task_225  225. 用队列实现栈
 * @Description: https://leetcode-cn.com/problems/implement-stack-using-queues/
 * @date 2022/1/26 15:59
 */
public class task_225 {

    static class MyStack {
        Deque<Integer> deque;

        public MyStack() {
            deque = new LinkedList<>();
        }

        public void push(int x) {
            deque.push( x );
        }

        public int pop() {
            return deque.pop();
        }

        public int top() {
            return deque.peekFirst();
        }

        public boolean empty() {
            return deque.isEmpty();
        }
    }
}
