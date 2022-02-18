package com.wp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_200 200. 岛屿数量
 * @Description: https://leetcode-cn.com/problems/number-of-islands/
 * @date 2022/2/17 15:20
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 */
public class task_200 {
    public static void main( String[] args ) {
        char[][] grid = {{'1','1','1','1','0'},
                         {'1','1','0','1','0'},
                         {'1','1','0','0','0'},
                         {'0','0','0','0','0'}};
        System.out.println( numIslands( grid ) );
    }

    public static int numIslands(char[][] grid) {
        int num = 0;
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] hasFind = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!hasFind[i][j] && grid[i][j]=='1') {
                    num++;
                    dfs(grid, i, j, hasFind);
                }
            }
        }
        return num;
    }

    private static void dfs( char[][] grid, int row, int col, boolean[][] hasFind ) {
        if (row<0 || col<0 || row>=grid.length || col>=grid[0].length || grid[row][col]=='0' || hasFind[row][col]) {
            return;
        }
        hasFind[row][col] = true;
        dfs( grid,row-1 , col, hasFind );
        dfs( grid, row, col+1, hasFind );
        dfs( grid, row+1, col, hasFind );
        dfs( grid, row, col-1, hasFind );
    }

    static class Island{
        int row;
        int col;
        char val;

        public Island( int row, int col, char val ) {
            this.row = row;
            this.col = col;
            this.val = val;
        }


    }
}
