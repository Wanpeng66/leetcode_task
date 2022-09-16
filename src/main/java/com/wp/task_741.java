package com.wp;


/**
 * @author: wp
 * @Title: task_741  741. 摘樱桃
 * @Description: https://leetcode.cn/problems/cherry-pickup/
 * @date 2022/7/10 15:46
 */
public class task_741 {
    public static void main( String[] args ) {
        int[][] grid = {{1,1,1,1,0,0,0},
                        {0,0,0,1,0,0,0},
                        {0,0,0,1,0,0,0},
                        {0,0,0,1,0,0,1},
                        {1,0,0,1,0,0,0},
                        {0,0,0,1,0,0,0},
                        {0,0,0,1,0,0,0},
                        {0,0,0,1,1,1,1}};
        System.out.println( cherryPickup( grid ) );
    }

    public static int cherryPickup(int[][] grid) {
        if (grid.length==1) {
            return grid[0][0];
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[] max = new int[]{0};
        int direction = 1,x = 0,y = 0;
        dfs(x,y,direction,grid,visited,max,0);
        return max[0];
    }

    private static void dfs( int x, int y, int direction, int[][] grid, boolean[][] visited, int[] max,int tmp ) {
        if (direction==-1&&x==0&&y==0) {
            if (tmp>max[0]) {
                max[0] = tmp;
            }
            return;
        }
        if (x==grid.length-1&&y==grid[0].length-1) {
            direction = -1;
        }
        int val = grid[x][y];
        if (val==-1) {
            return;
        }
        if (val==1 && !visited[x][y]) {
            tmp++;
            visited[x][y] = true;
        }
        if (direction==1) {
            if (x+1<grid.length) {
                dfs(x+1,y,direction,grid,visited,max,tmp);
            }
            if (y+1<grid[0].length) {
                dfs( x,y+1,direction,grid,visited,max,tmp );
            }
        }else{
            if (x-1>=0) {
                dfs(x-1,y,direction,grid,visited,max,tmp);
            }
            if (y-1>=0) {
                dfs(x,y-1,direction, grid,visited,max,tmp );
            }
        }
        visited[x][y] = false;
    }
}
