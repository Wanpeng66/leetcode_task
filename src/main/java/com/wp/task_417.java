package com.wp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wp
 * @Title: task_417  417. 太平洋大西洋水流问题
 * @Description:  https://leetcode-cn.com/problems/pacific-atlantic-water-flow/
 * @date 2022/4/27 17:50
 */
public class task_417 {

    public static void main( String[] args ) {
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        List<List<Integer>> lists = pacificAtlantic( heights );
        System.out.println( lists );
    }

    public static List<List<Integer>> pacificAtlantic(int[][] array) {
        int rowMax = array.length;
        int colMax = array[0].length;
        //能到达太平洋的格子
        boolean[][] pacific = new boolean[rowMax][colMax];
        //能到达大西洋的格子
        boolean[][] atlantic = new boolean[rowMax][colMax];
        //记录访问过的格子
        boolean[][] visited = new boolean[rowMax][colMax];
        //第一行都能到达太平洋，反向逆推，看那些格子能到太平洋
        for (int i = 0; i < colMax; i++) {
            dfs( array, 0, i, visited, array[0][i], pacific );
        }
        //第一列也都能到达太平洋，反向逆推，看那些格子能到太平洋
        for (int i = 0; i < rowMax; i++) {
            dfs(array, i, 0, visited, array[i][0], pacific);
        }
        visited = new boolean[rowMax][colMax];
        //最后一行都能到达大西洋，反向逆推，看那些格子能到大西洋
        for (int i = 0; i < colMax; i++) {
            dfs(array, rowMax-1, i, visited, array[rowMax-1][i], atlantic );
        }
        for (int i = 0; i < rowMax; i++) {
            dfs(array, i, colMax-1, visited, array[i][colMax-1], atlantic );
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add( Arrays.asList(i, j) );
                }
            }
        }
        return res;
    }

    public static void dfs( int[][] array, int row, int col, boolean[][] visited, int val, boolean[][] ocean ) {
        if (visited[row][col] || array[row][col]<val) {
            return;
        }
        ocean[row][col] = true;
        visited[row][col] = true;
        //往上走
        if (row-1>=0) {
            dfs(array, row-1, col, visited, array[row][col], ocean );
        }
        //往下走
        if (row+1<array.length) {
            dfs(array, row+1, col, visited, array[row][col], ocean );
        }
        //往左走
        if (col-1>=0) {
            dfs(array, row, col-1, visited, array[row][col], ocean );
        }
        //往右走
        if (col+1<array[0].length) {
            dfs(array, row, col+1, visited, array[row][col], ocean );
        }
    }
}
