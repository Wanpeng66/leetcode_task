package com.wp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_240 240. 搜索二维矩阵 II
 * @Description: https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * @date 2022/2/21 10:53
 */
public class task_240 {
    public static void main( String[] args ) {
        int[][] matrix = {{-1,3}};
        System.out.println( searchMatrix( matrix, 3 ) );
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int col = matrix[0].length-1,row = 0;
        while (col>=0 && row< matrix.length) {
            if (matrix[row][col]==target) {
                return true;
            } else if (matrix[row][col]>target) {
                col--;
            }else{
                row++;
            }
        }
        return  false;
    }

    private boolean method1( int[][] matrix, int target ) {
        Map<String, Boolean> res = new HashMap<>();
        return search( matrix, 0, 0, target, res);
    }

    private boolean search( int[][] matrix, int row, int col, int target, Map<String, Boolean> res ) {
        String key = row+"_"+col;
        if (res.containsKey( key )) {
            return res.get( key );
        }
        if (row>=matrix.length || col>=matrix[0].length) {
            res.put( key, false );
            return false;
        }
        if (matrix[row][col]==target) {
            return true;
        }
        boolean flag = search( matrix,row, col+1, target, res ) || search( matrix, row+1, col, target, res );
        res.put( key,flag );
        return flag;
    }
}
