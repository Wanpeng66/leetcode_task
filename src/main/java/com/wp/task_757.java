package com.wp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: wp
 * @Title: task_757
 * @Description:
 * @date 2022/7/22 18:01
 */
public class task_757 {
    public static void main( String[] args ) {
        int[][] intervals = {{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}};
        System.out.println( intersectionSizeTwo( intervals ) );
    }
    public static int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort( intervals, new Comparator<int[]>() {
            @Override
            public int compare( int[] o1, int[] o2 ) {
                return o1[0]==o2[0]?o2[1]-o1[1]:o1[0]-o2[0];
            }
        } );
        int[] interval = intervals[0];
        int left = interval[0]-1;
        interval = intervals[intervals.length-1];
        int right = interval[0]+1;
        return right-left+1;
    }
}
