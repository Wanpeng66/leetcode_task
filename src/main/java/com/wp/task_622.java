package com.wp;

import java.util.Queue;

/**
 * @author: wp
 * @Title: task_622 622. 设计循环队列
 * @Description: https://leetcode-cn.com/problems/design-circular-queue/
 * @date 2022/1/26 15:22
 */
public class task_622 {

    public static void main( String[] args ) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        System.out.println( circularQueue.enQueue( 2 ) );
        System.out.println( circularQueue.Rear() );
        System.out.println( circularQueue.Front() );
    }


    static class MyCircularQueue {
        int[] array;
        int head;
        int tail;
        int size;

        public MyCircularQueue(int k) {
            array = new int[k];
            head=tail=size=0;
        }

        public boolean enQueue(int value) {
            if (size==array.length) {
                return false;
            }
            array[tail] = value;
            size++;
            tail = tail+1>=array.length?0:tail+1;
            return true;
        }

        public boolean deQueue() {
            if (size==0) {
                return false;
            }
            size--;
            head = head+1>=array.length?0:head+1;
            return true;
        }

        public int Front() {
            if (size==0) {
                return -1;
            }
            return array[head];
        }

        public int Rear() {
            if (size==0) {
                return -1;
            }
            return array[tail==0?array.length-1:tail-1 ];
        }

        public boolean isEmpty() {
            return size==0;
        }

        public boolean isFull() {
            return size == array.length;
        }
    }
}
