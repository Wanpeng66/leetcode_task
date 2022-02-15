package com.wp;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: wp
 * @Title: task_295 295. 数据流的中位数
 * @Description: https://leetcode-cn.com/problems/find-median-from-data-stream/
 * @date 2022/2/15 11:44
 */
public class task_295 {
    public static void main( String[] args ) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum( 1 );
        medianFinder.addNum( 2 );
        System.out.println( medianFinder.findMedian() );
        medianFinder.addNum( 3 );
        System.out.println( medianFinder.findMedian() );
    }

     static class MedianFinder {
        int size = 0;
        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>( Comparator.reverseOrder() );
        }

        public void addNum(int num) {
            if (0==size) {
                maxHeap.offer( num );
            }else{
                if (num>maxHeap.peek()) {
                    minHeap.offer( num );
                    maxHeap.offer( minHeap.poll() );
                }else{
                    maxHeap.offer( num );
                }
            }
            size++;
            int maxMaxNum;
            if (size!=1) {
                maxMaxNum = size - (size/2);
                int tmp = maxMaxNum - maxHeap.size();
                if (tmp<0) {
                    for (int i = tmp; i < 0; i++) {
                        minHeap.offer( maxHeap.poll() );
                    }
                }
            }
        }

        public double findMedian() {
            double middle = 0;
            if (size % 2==0) {
                middle = ((double)maxHeap.peek()/2 + (double)minHeap.peek()/2);
            }else{
                middle = maxHeap.peek();
            }
            return middle;
        }
    }
}
