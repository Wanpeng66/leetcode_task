package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_1122 1122. 数组的相对排序
 * @Description: https://leetcode-cn.com/problems/relative-sort-array/
 * @date 2022/1/30 12:41
 */
public class task_1122 {

    public static void main( String[] args ) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        int[] ints = relativeSortArray( arr1, arr2 );
        System.out.println( ints );
    }

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int max = 1001;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            list.add( i,0 );
        }
        for (int i : arr1) {
            list.set( i,list.get( i )+1 );
        }
        int offset = 0;
        for (int i = 0; i < arr2.length; i++) {
            int j = arr2[i];
            Integer size = list.get( j );
            for (Integer k = 0; k < size; k++) {
                arr1[offset] = j;
                offset++;
            }
            list.set( j,0 );
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get( i )>0) {
                Integer size = list.get( i );
                for (Integer k = 0; k < size; k++) {
                    arr1[offset] = i;
                    offset++;
                }
            }
        }
        return  arr1;
    }
}
