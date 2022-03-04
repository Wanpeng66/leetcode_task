package com.wp;

import java.util.Arrays;

/**
 * @author: wp
 * @Title: task_435 435. 无重叠区间
 * @Description: https://leetcode-cn.com/problems/non-overlapping-intervals/
 * @date 2022/2/28 13:58
 */
public class task_435 {
    public static void main( String[] args ) {
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println( eraseOverlapIntervals( intervals ) );
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort( intervals, ( o1, o2 ) -> o1[1]==o2[1]?o1[0]-o2[0]:o1[1]-o2[1] );
        int[] pre = null;
        int[] max = new int[1];
        eraseOverlapIntervals(intervals,0,pre,0,max);
        return intervals.length-max[0];
    }

    private static void eraseOverlapIntervals( int[][] intervals, int offset, int[] pre,int tmp, int[] max ) {
        if (offset>=intervals.length) {
            if (tmp>max[0]) {
                max[0] =  tmp;
            }
            return;
        }
        int[] interval = intervals[offset];
        if (pre==null || interval[0]>=pre[1]) {
            eraseOverlapIntervals( intervals, offset+1, intervals[offset], tmp+1, max );
        }else{
            eraseOverlapIntervals( intervals, offset+1, pre, tmp, max );
        }
    }
}
