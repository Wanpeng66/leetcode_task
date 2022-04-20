package com.wp;

import java.util.List;

/**
 * @author: wp
 * @Title: task_388  388. 文件的最长绝对路径
 * @Description: https://leetcode-cn.com/problems/longest-absolute-file-path/
 * @date 2022/4/20 12:03
 */
public class task_388 {
    public static void main( String[] args ) {
        System.out.println( lengthLongestPath( "a" ) );
    }
    public static int lengthLongestPath(String input) {
        String[] array = input.split( "\n" );
        int path = 1;
        int level = 0;
        String prefix = "";
        for (String str : array) {
            int length = str.length();
            str = str.replaceAll( "\t","" );
            int currentLevel = length - str.length();
            if (currentLevel<=level && !prefix.equalsIgnoreCase( "" )) {
                for (int i = 0; i <= Math.abs( currentLevel - level ); i++) {
                    prefix = prefix.substring( 0, prefix.lastIndexOf( "/" ) );
                }
            }
            level = currentLevel;
            prefix = prefix.concat( "/" ).concat( str );
             if (str.contains( "." ) && prefix.length()>path) {
                path = prefix.length();
            }
        }
        return path-1;
    }
}
