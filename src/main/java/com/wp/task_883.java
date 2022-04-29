package com.wp;

/**
 * @author: wp
 * @Title: task_883   883. 三维形体投影面积
 * @Description: https://leetcode-cn.com/problems/projection-area-of-3d-shapes/
 * @date 2022/4/26 13:47
 */
public class task_883 {
    public static void main( String[] args ) {
        int[][] grid = {{1, 0},{0, 2}};
        System.out.println( projectionArea( grid ) );
    }
    public static int projectionArea(int[][] grid) {
        int xy = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]!=0) {
                    xy++;
                }
            }
        }
        int xz = 0;
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            max = 0;
            int[] ints = grid[i];
            for (int j = 0; j < ints.length; j++) {
                if (ints[j]>max) {
                    max = ints[j];
                }
            }
            xz += max;
        }
        int yz = 0;
        for (int i = 0; i < grid[0].length; i++) {
            max = 0;
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i]>max) {
                    max = grid[j][i];
                }
            }
            yz += max;
        }
        return  xy + xz + yz;
    }
}
