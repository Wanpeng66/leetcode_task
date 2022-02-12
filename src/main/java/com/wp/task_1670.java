package com.wp;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_1670 1670. 设计前中后队列
 * @Description: https://leetcode-cn.com/problems/design-front-middle-back-queue/
 * @date 2022/1/27 15:45
 */
public class task_1670 {

    public static void main( String[] args ) {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushFront(1);   // [1]
        q.pushBack(2);    // [1, 2]
        q.pushMiddle(3);  // [1, 3, 2]
        q.pushMiddle(4);  // [1, 4, 3, 2]
        System.out.println( q.popFront() );
        System.out.println( q.popMiddle() );
        System.out.println( q.popMiddle() );
        System.out.println( q.popBack() );
        System.out.println( q.popFront() );
    }

    static class FrontMiddleBackQueue {

        Deque<Integer> deque ;

        public FrontMiddleBackQueue() {
            deque = new LinkedList<>();
        }

        public void pushFront(int val) {
            deque.push( val );
        }

        public void pushMiddle(int val) {
            if (deque.isEmpty()) {
                deque.push( val );
            }else{
                int size = deque.size();
                if (size==1) {
                    deque.push( val );
                    return;
                }
                int middle = size/2;
                List<Integer> tmp = new ArrayList<>();
                for (int i = 0; i < middle; i++) {
                    tmp.add( i,deque.pop() );
                }
                deque.push( val );
                for (int i = tmp.size() - 1; i >= 0; i--) {
                    deque.push( tmp.get( i ) );
                }
            }
        }

        public void pushBack(int val) {
            deque.addLast( val );
        }

        public int popFront() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.pop();
        }

        public int popMiddle() {
            if (deque.isEmpty()) {
                return -1;
            }
            int size = deque.size();
            if (size==1) {
                return deque.pop();
            }
            int middle = size%2==0?size/2-1:size/2;
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < middle; i++) {
                tmp.add( i,deque.pop() );
            }
            Integer val = deque.pop();
            for (int i = tmp.size() - 1; i >= 0; i--) {
                deque.push( tmp.get( i ) );
            }
            return val;
        }

        public int popBack() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.pollLast();
        }
    }
}
