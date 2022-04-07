package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_187  187. 重复的DNA序列
 * @Description: https://leetcode-cn.com/problems/repeated-dna-sequences/
 * @date 2022/4/6 13:55
 */
public class task_187 {
    public static void main( String[] args ) {
        List<String> list = findRepeatedDnaSequences( "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT" );
        System.out.println( list );
    }
    public static List<String> findRepeatedDnaSequences( String s) {
        Map<String,Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append( chars[i] );
            if (sb.length()==10) {
                String str = sb.toString();
                Integer num = map.getOrDefault( str, 0 );
                if (num==1) {
                    res.add( str );
                }
                map.put( str, num+1  );
                sb.deleteCharAt( 0 );
            }
        }
        return res;
    }
}
