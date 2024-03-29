package com.wp;

/**
 * @author: wp
 * @Title: task_1254 1254. 统计封闭岛屿的数目
 * @Description: https://leetcode-cn.com/problems/number-of-closed-islands/
 * @date 2022/2/17 17:21
 */
public class task_1254 {

    public static void main( String[] args ) {
        int[][] grid = {{1,1,1,1,1,1,1,0},
                        {1,0,0,0,0,1,1,0},
                        {1,0,1,0,1,1,1,0},
                        {1,0,0,0,0,1,0,1},
                        {1,1,1,1,1,1,1,0}};
        System.out.println( closedIsland( grid ) );
    }

    public static int closedIsland(int[][] grid) {
        int num = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ( grid[i][j]==0 && dfs(grid, i, j )) {
                    num++;
                }
            }
        }
        return num;
    }

    private static boolean dfs( int[][] grid, int row, int col ) {
        if (row<0 || col<0 || row>grid.length-1 || col>grid[0].length-1  ) {
            return false;
        }
        if (grid[row][col]==1 ) {
            return true;
        }else{
            grid[row][col] = 1;
            return dfs( grid, row - 1, col ) && dfs( grid, row, col + 1 )
                    && dfs( grid, row + 1, col ) && dfs( grid, row, col - 1 );
        }
    }
}
