package com.wp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: wp
 * @Title: task_937  937. 重新排列日志文件
 * @Description: https://leetcode-cn.com/problems/reorder-data-in-log-files/
 * @date 2022/5/3 16:35
 */
public class task_937 {
    public static void main( String[] args ) {
        String[] logs = {"dig1 8 1 5 1","let1 art zero can","dig2 3 6","let2 own kit dig","let3 art zero"};
        String[] array = reorderLogFiles( logs );
        System.out.println( array );
    }
    public static String[] reorderLogFiles(String[] logs) {
        List<String> dig = new ArrayList<>();
        List<String> words = new ArrayList<>();
        String reg = "^[0-9]*$";
        for (String log : logs) {
            String str = log.split( " " )[1];
            if (str.matches( reg )) {
                dig.add( log );
            }else{
                words.add( log );
            }
        }
        words.sort( ( str1, str2 ) -> {
            String[] s1 = str1.split( " " );
            String[] s2 = str2.split( " " );
            int size = Math.max( s1.length, s2.length );
            for (int i = 1; i < size; i++) {
                if (i>=s1.length && i<s2.length) {
                    return -1;
                } else if (i>=s2.length && i<s1.length) {
                    return 1;
                }else if (!s1[i].equalsIgnoreCase( s2[i] )) {
                    return s1[i].compareTo( s2[i] );
                }
            }
            return s1[0].compareTo( s2[0] );
        } );
        words.addAll( dig );
        return words.toArray( new String[0] );
    }
}
