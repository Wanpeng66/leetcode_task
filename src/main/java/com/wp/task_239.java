package com.wp;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: wp
 * @Title: task_239  239. 滑动窗口最大值
 * @Description: https://leetcode-cn.com/problems/sliding-window-maximum/
 * @date 2022/2/15 12:53
 */
public class task_239 {

    public static void main( String[] args ) {
        int[] array = {9,10,9,-7,-4,-8,2,-6};
        int[] maxSlidingWindow = maxSlidingWindow( array, 5 );
        System.out.println( maxSlidingWindow );
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int size = nums.length;
        int num = size-k;
        int[] res = new int[num+1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>( Comparator.reverseOrder() );
        for (int i = 0; i <= num; i++) {
            if (i==0) {
                for (int j = 0; j < k; j++) {
                    maxHeap.offer( nums[i+j] );
                }
            }else{
                maxHeap.offer( nums[i+k-1] );
                maxHeap.remove(nums[i-1]);
            }
            res[i] = maxHeap.peek();
        }
        return  res;
    }
}
