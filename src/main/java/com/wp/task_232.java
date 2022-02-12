package com.wp;

import java.util.Stack;

/**
 * @author: wp
 * @Title: task_232 232. 用栈实现队列
 * @Description: https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * @date 2022/1/25 14:33
 */
public class task_232 {
    public static void main( String[] args ) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        System.out.println( obj.peek() );
        System.out.println( obj.pop() );
        System.out.println( obj.empty() );

    }


    static class MyQueue {
        Stack<Integer> main;
        Stack<Integer> tmp;
        boolean isPush = false;

        public MyQueue() {
            main = new Stack<>();
            tmp = new Stack<>();
        }

        public void push(int x) {
            if (!isPush && !tmp.isEmpty()) {
                while (!tmp.isEmpty()) {
                    main.push( tmp.pop() );
                }
            }
            isPush = true;
            main.push( x );
        }

        public int pop() {
            if (isPush && !main.isEmpty()) {
                while (!main.isEmpty()) {
                    tmp.push( main.pop() );
                }
            }
            isPush = false;
            return tmp.pop();
        }

        public int peek() {
            int pop = pop();
            tmp.push( pop );
            return pop;
        }

        public boolean empty() {
            return main.isEmpty() && tmp.isEmpty();
        }
    }
}
