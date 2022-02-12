package com.wp;

import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

/**
 * @author: wp
 * @Title: task_155  155. 最小栈
 * @Description: https://leetcode-cn.com/problems/min-stack/
 * @date 2022/1/25 10:27
 */
public class task_155 {

    public static void main( String[] args ) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        System.out.println( minStack.getMin() );
        minStack.pop();
        System.out.println( minStack.getMin() );
        minStack.pop();
        System.out.println( minStack.getMin() );
    }

    static class MinStack {
        Stack<Integer> stack;
        Integer min = null;
        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int val) {
            if (Objects.isNull(min) || val<min) {
                min = val;
            }
            stack.push( val );
        }

        public void pop() {
            Integer pop = stack.pop();
            if (pop.equals( min )) {
                min = null;
            }
            Iterator<Integer> iterator = stack.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (Objects.isNull(min) || next<min) {
                    min = next;
                }
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }
}
