package com.wp;

/**
 * @author: wp
 * @Title: task_1728  1728. 猫和老鼠 II
 * @Description: https://leetcode.cn/problems/cat-and-mouse-ii/
 * @date 2022/5/10 13:51
 */
public class task_1728 {

    public static void main( String[] args ) {
        String[] grid = {"####F","#C...","M...."};
        System.out.println( canMouseWin( grid, 1, 2 ) );
    }
    public static boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int row = grid.length;
        int col = grid[0].length();
        int cRow = 0,cCol=0,mRow=0,mCol=0;
        String[][] array = new String[row][col];
        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                array[i][j] = chars[j]+"";
                if (chars[j]=='C') {
                    cRow = i;
                    cCol = j;
                } else if (chars[j]=='M') {
                    mRow = i;
                    mCol = j;
                }
            }
        }
        int[] mouseWin = {0};
        boolean[][][] visited = new boolean[2][row][col];
        backTrace(array, cRow, cCol, mRow, mCol, 'M', visited, catJump, mouseJump, 0, mouseWin);
        return mouseWin[0] == 1;
    }

    private static void backTrace( String[][] array, int cRow, int cCol, int mRow, int mCol, char target, boolean[][][] visited, int catJump, int mouseJump, int jumpNum, int[] mouseWin ) {
        if (mouseWin[0]!=0) {
            return;
        }
        if (target=='M') {
            //如果在1000次移动内先找到食物
            if (array[mRow][mCol].equalsIgnoreCase( "F" ) && jumpNum<1000) {
               mouseWin[0] = 1;
            }else if(jumpNum<1000){
                //往上走
                for (int i = 0; i < mouseJump; i++) {
                    //不能越界 也不能和猫在一个位置上
                    if (mRow-i>=0 && (mRow-i!=cRow && mCol!=cCol) && !array[mRow-i][mCol].equalsIgnoreCase( "#" )) {
                        visited[0][mRow-i][mCol] = true;
                        backTrace( array, cRow, cCol, mRow-i, mCol, 'C', visited, catJump, mouseJump, jumpNum+1, mouseWin );
                        visited[0][mRow-i][mCol] = false;
                    }
                }
                //往下走
                for (int i = 0; i < mouseJump; i++) {
                    //不能越界 也不能和猫在一个位置上
                    if (mRow+i<array.length && (mRow+i!=cRow && mCol!=cCol) && !array[mRow+i][mCol].equalsIgnoreCase( "#" )) {
                        visited[0][mRow+i][mCol] = true;
                        backTrace( array, cRow, cCol, mRow+i, mCol, 'C', visited, catJump, mouseJump, jumpNum+1, mouseWin );
                        visited[0][mRow+i][mCol] = false;
                    }
                }
                //往左走
                for (int i = 0; i < mouseJump; i++) {
                    if (mCol-i>=0 && (mRow!=cRow && mCol-i!=cCol) && !array[mRow][mCol-i].equalsIgnoreCase( "#" )) {
                        visited[0][mRow][mCol-i] = true;
                        backTrace( array, cRow, cCol, mRow, mCol-i, 'C', visited, catJump, mouseJump, jumpNum+1, mouseWin );
                        visited[0][mRow][mCol-i] = false;
                    }
                }
                //往右走
                for (int i = 0; i < mouseJump; i++) {
                    if (mCol+i<array[0].length && (mRow!=cRow && mCol+i!=cCol) && !array[mRow][mCol+i].equalsIgnoreCase( "#" )) {
                        visited[0][mRow][mCol+i] = true;
                        backTrace( array, cRow, cCol, mRow, mCol+i, 'C', visited, catJump, mouseJump, jumpNum+1, mouseWin );
                        visited[0][mRow][mCol+i] = false;
                    }
                }
            }

        } else if (target=='C') {
            //如果猫先找到食物或者跟老鼠到了同一个格子
            if (array[cRow][cCol].equalsIgnoreCase( "F" ) || (cRow==mRow && cCol==mCol)) {
                mouseWin[0] = 2;
            }else{
                //往上走
                for (int i = 0; i < catJump; i++) {
                    if (cRow-i>=0 && !array[cRow-i][cCol].equalsIgnoreCase( "#" )) {
                        visited[1][cRow-i][cCol] = true;
                        backTrace( array, cRow-i, cCol, mRow, mCol, 'M', visited, catJump, mouseJump, jumpNum, mouseWin );
                        visited[1][cRow-i][cCol] = false;
                    }
                }
                //往下走
                for (int i = 0; i < catJump; i++) {
                    if (cRow+i<array.length && !array[cRow+i][cCol].equalsIgnoreCase( "#" )) {
                        visited[1][cRow+i][cCol] = true;
                        backTrace( array, cRow+i, cCol, mRow, mCol, 'M', visited, catJump, mouseJump, jumpNum, mouseWin );
                        visited[1][cRow+i][cCol] = false;
                    }
                }
                //往左走
                for (int i = 0; i < catJump; i++) {
                    if (cCol-i>=0 && !array[cRow][cCol-i].equalsIgnoreCase( "#" )) {
                        visited[1][cRow][cCol-i] = true;
                        backTrace( array, cRow, cCol-i, mRow, mCol, 'M', visited, catJump, mouseJump, jumpNum, mouseWin );
                        visited[1][cRow][cCol-i] = false;
                    }
                }
                //往右走
                for (int i = 0; i < catJump; i++) {
                    if (cCol+i>=array[0].length && !array[cRow][cCol+i].equalsIgnoreCase( "#" )) {
                        visited[1][cRow][cCol+i] = true;
                        backTrace( array, cRow, cCol+i, mRow, mCol, 'M', visited, catJump, mouseJump, jumpNum, mouseWin );
                        visited[1][cRow][cCol+i] = false;
                    }
                }
            }
        }
    }
}
