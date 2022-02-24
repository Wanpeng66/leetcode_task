package com.wp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author: wp
 * @Title: task_64 64. 最小路径和
 * @Description: https://leetcode-cn.com/problems/minimum-path-sum/
 * @date 2022/2/22 11:03
 */
public class task_64 {

    public static void main( String[] args ) {
        int[][] grid = {{1,2,3},{4,5,6}};
        System.out.println( minPathSum3( grid ) );
    }

    public static int minPathSum3(int[][] grid) {
        int rowMax = grid.length;
        int colMax = grid[0].length;
        int[][] steps = new int[rowMax][colMax];
        steps[0][0] = grid[0][0];
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                int val = grid[i][j];
                if (i==0&&j==0) {
                }else if (i==0) {
                    steps[i][j] = steps[i][j-1] + val;
                }else if (j==0) {
                    steps[i][j] = steps[i-1][j] + val;
                }else{
                    int min = steps[i-1][j];
                    if (steps[i][j-1]<min) {
                        min = steps[i][j-1];
                    }
                    steps[i][j] = min + val;
                }

            }
        }
        return steps[rowMax-1][colMax-1];
    }

    public static int minPathSum2(int[][] grid) {
        Map<String,Integer> map = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer( new int[]{0, 0, grid[0][0]} );
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int row = poll[0];
                int col = poll[1];
                int step = poll[2];
                String key = row+"_"+col;
                if (map.containsKey( key )) {
                    if (map.get( key )<=step) {
                        continue;
                    }
                }
                map.put( key, step );
                if (row+1<grid.length) {
                    queue.offer( new int[]{row+1, col, step+grid[row+1][col]} );
                }
                if (col+1<grid[0].length) {
                    queue.offer( new int[]{row, col+1, step+grid[row][col+1]} );
                }
            }
        }

        return map.get( (grid.length-1)+"_"+(grid[0].length-1) );
    }

    public static int minPathSum(int[][] grid) {
        Map<String,Integer> map = new HashMap<>();
        int[] Min = {Integer.MAX_VALUE};
        minPathSum( grid, 0, 0, 0, Min, map );
        return Min[0];
    }

    private static void minPathSum( int[][] grid, int row, int col, int step, int[] min, Map<String, Integer> map ) {
        if (row==grid.length-1 && col==grid[0].length-1) {
            step += grid[grid.length-1][grid[0].length-1];
            if (step<min[0]) {
                min[0] = step;
            }
            return;
        }
        step += grid[row][col];
        String key = row+"_"+col;
        if (map.containsKey( key )) {
            Integer val = map.get( key );
            if (val<=step) {
                return;
            }else{
                map.put( key,step );
            }
        }else{
            map.put( key,step );
        }
        if (row+1<grid.length&&col+1<grid[0].length) {
            if (grid[row+1][col]<grid[row][col]) {
                minPathSum( grid,row+1,col,step, min, map );
                minPathSum( grid,row,col+1,step, min, map );
            }else{
                minPathSum( grid,row,col+1,step, min, map );
                minPathSum( grid,row+1,col,step, min, map );
            }
        }
        if (row+1<grid.length) {
            minPathSum( grid,row+1,col,step, min, map );
        }
        if (col+1<grid[0].length) {
            minPathSum( grid,row,col+1,step, min, map );
        }
    }

    private void dpMinPathSum( int[][] grid ) {
        //递归树每一层的状态集合
        int rowMax = grid.length + grid[0].length;
        int colMax = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                colMax+= grid[i][j];
            }
        }
        int[][] step = new int[rowMax+colMax][colMax];
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                step[i][j] = -1;
            }
        }
        step[0][0] = grid[0][0];
        int row=0,col=0;
    }
}
