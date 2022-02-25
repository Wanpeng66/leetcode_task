package com.wp;

/**
 * @author: wp
 * @Title: task_74  74. 搜索二维矩阵
 * @Description: https://leetcode-cn.com/problems/search-a-2d-matrix/
 * @date 2022/2/25 11:42
 */
public class task_74 {
    public static void main( String[] args ) {
        //int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        int[][] matrix = {{1,3}};
        System.out.println( searchMatrix( matrix, 3 ) );
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int r = -1;
        int perfix = 0,end = row-1;
        while (perfix<=end) {
            int middle = perfix + (end - perfix)/2;
            if (matrix[middle][col-1]>=target) {
                if (middle==0 || matrix[middle-1][col-1]<target) {
                    r = middle;
                    break;
                }else{
                    end = middle-1;
                }
            }else{
                perfix = middle+1;
            }
        }
        if (r==-1) {
            return false;
        }
        perfix = 0;end = col-1;
        while (perfix<=end) {
            int middle = perfix + (end - perfix)/2;
            if (matrix[r][middle]>target) {
                end = middle-1;
            } else if (matrix[r][middle]<target) {
                perfix = middle+1;
            }else{
                return true;
            }
        }
        return false;
    }
}
