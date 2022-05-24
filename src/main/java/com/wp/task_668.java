package com.wp;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author: wp
 * @Title: task_668  668. 乘法表中第k小的数
 * @Description: https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/
 * @date 2022/5/18 10:46
 */
public class task_668 {
    public static void main( String[] args ) {
        System.out.println( findKthNumber( 9895, 28405, 100787757 ) );
    }
    public static int findKthNumber(int m, int n, int k) {
        ArrayList<Long> list = new ArrayList<>( );
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                list.add( (long) (i*j) );
            }
        }
        list.sort( Comparator.naturalOrder() );
        return list.get( k-1 ).intValue();
    }
}
