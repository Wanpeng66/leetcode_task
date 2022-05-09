package com.wp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: task_433   433. 最小基因变化
 * @Description: https://leetcode-cn.com/problems/minimum-genetic-mutation/
 * @date 2022/5/7 13:35
 */
public class task_433 {

    public static void main( String[] args ) {
        System.out.println( minMutation( "AAAAAAAA", "CCCCCCCC", new String[]{"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA"} ) );
    }
    public static int minMutation(String start, String end, String[] bank) {
        if (bank.length==0 || Arrays.stream( bank ).noneMatch( s -> s.equalsIgnoreCase( end ) )) {
            return -1;
        }
        List<String> list = Arrays.stream( bank ).sorted().collect( Collectors.toList() );
        list.remove( end );
        list.remove( start );
        List<String> tmp = Arrays.asList( end );
        int[] num = {Integer.MAX_VALUE};
        dfs(start, list, tmp, num, 0 );
        return num[0]!=Integer.MAX_VALUE?num[0]:-1;
    }

    private static void dfs( String start, List<String> list, List<String> tmp, int[] num, int k ) {
        for (String str : tmp) {
            if (change(str, start)==1) {
                k++;
                if (k<num[0]) {
                    num[0] = k;
                }
                return;
            }
            List<String> tmpList = new ArrayList<>();
            for (String s : list) {
                if (change(str, s)==1 ) {
                    tmpList.add( s );
                }
            }
            if (tmpList.size()>0) {
                list.removeAll( tmpList );
                dfs( start, list, tmpList, num, ++k );
                list.addAll( tmpList );
            }
        }

    }

    private static int change( String str, String start ) {
        int num = 0;
        for (int i = 0; i < start.length(); i++) {
            if (str.charAt( i )!=start.charAt( i )) {
                num++;
            }
        }
        return num;
    }
}
