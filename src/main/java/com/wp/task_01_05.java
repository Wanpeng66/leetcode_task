package com.wp;

import java.util.Arrays;

/**
 * @author: wp
 * @Title: task_01_05  面试题 01.05. 一次编辑
 * @Description: https://leetcode.cn/problems/one-away-lcci/
 * @date 2022/5/13 18:18
 */
public class task_01_05 {
    public  static void main( String[] args ) {
        System.out.println( oneEditAway( "pales", "pal" ) );
    }
    public static boolean oneEditAway(String first, String second) {
        if (Math.abs( first.length()-second.length() )>1) {
            return false;
        }
        if (first.length()==second.length() && different( first, second )<=1) {
            return true;
        }else if(first.length()!=second.length()){
            String longStr = first.length()>second.length()?first:second;
            String shortStr = first.length()<second.length()?first:second;
            StringBuilder sb = new StringBuilder( longStr );
            for (int i = 0; i < longStr.length(); i++) {
                sb.deleteCharAt( i );
                if (different( sb.toString(),shortStr )==0) {
                    return true;
                }
                sb.insert( i, longStr.charAt( i ) );
            }
        }
        return false;
    }

    public static int different(String first, String second){
        int num = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt( i )!=second.charAt( i )) {
                num++;
            }
        }
        return num;
    }
}
