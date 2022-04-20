package com.wp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_821  821. 字符的最短距离
 * @Description: https://leetcode-cn.com/problems/shortest-distance-to-a-character/
 * @date 2022/4/19 17:14
 */
public class task_821 {
    public static void main( String[] args ) {
        String s = "loveleetcode";
        char c = 'e';
        int[] array = shortestToChar( s, c );
        System.out.println( array );
    }
    public static int[] shortestToChar(String s, char c) {
        int[] res = new int[s.length()];
        char[] chars = s.toCharArray();
        List<Integer> offset = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]==c) {
                offset.add( i );
            }
        }
        Integer index = 0;
        for (int i = 0; i < chars.length; i++) {
            Integer o1 = offset.get( index );
            Integer o2 = index+1<offset.size()?offset.get( index+1 ):Integer.MAX_VALUE;
            res[i] = Math.min( Math.abs( o1-i ), Math.abs( o2-i ) );
            if (Math.abs( o1-i )>Math.abs( o2-i )) {
                index++;
            }
        }
        return res;
    }
}
