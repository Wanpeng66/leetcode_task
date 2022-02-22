package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_51 51. N 皇后
 * @Description: https://leetcode-cn.com/problems/n-queens/
 * @date 2022/2/21 15:10
 */
public class task_51 {
    public static void main( String[] args ) {
        System.out.println( solveNQueens( 4 ) );
    }

    public static List<List<String>> solveNQueens( int n) {
        List<List<String>> res = new ArrayList<>();
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = -1;
        }
        getIndex(n, 0, index, res);
        return res;
    }

    private static void getIndex( int n, int row, int[] index, List<List<String>> res ) {
        if (row>=n) {
            List<String> list = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i : index) {
                sb.setLength( 0 );
                for (int j = 0; j < n; j++) {
                    if (j!=i) {
                        sb.append( "." );
                    }else{
                        sb.append( "Q" );
                    }
                }
                list.add( sb.toString() );
            }
            res.add( list );
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isOk(n, row, i, index )) {
                index[row] = i;
                getIndex( n, row+1, index, res );
            }
        }
    }

    private static boolean isOk( int n,int row, int col, int[] index ) {
        int left = col-1,right = col+1;
        for (int i = row-1; i >= 0; i--) {
            if (index[i]==col) {
                return false;
            }
            if (left>=0 && index[i]==left) {
                return false;
            }
            if (right<n && index[i]==right) {
                return false;
            }
            left--;
            right++;
        }
        return true;
    }
}
