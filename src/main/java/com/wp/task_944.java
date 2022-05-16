package com.wp;

/**
 * @author: wp
 * @Title: task_944  944. 删列造序
 * @Description: https://leetcode.cn/problems/delete-columns-to-make-sorted/
 * @date 2022/5/12 15:11
 */
public class task_944 {
    public static void main( String[] args ) {
        String[] strs = {"cba","daf","ghi"};
        System.out.println( minDeletionSize( strs ) );
    }
    public static int minDeletionSize(String[] strs) {
        char[][] array = new char[strs.length][strs[0].length()];
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            array[i] = chars;
        }
        int col = strs[0].length();
        int row = strs.length;
        int num = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row-1; j++) {
                if (array[j][i]>array[j+1][i]) {
                    num++;
                    break;
                }
            }
        }
        return num;
    }
}
