package com.wp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_17_11  面试题 17.11. 单词距离
 * @Description: https://leetcode.cn/problems/find-closest-lcci/
 * @date 2022/5/27 16:44
 */
public class task_17_11 {
    public static void main( String[] args ) {
        String[] words = {"I","am","a","student","from","a","university","in","a","city"};
        String word1 = "a";
        String word2 = "student";
        System.out.println( findClosest( words, word1, word2 ) );
    }
    public static int findClosest(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!(word.equalsIgnoreCase( word1 )||word.equalsIgnoreCase( word2 ))) {
                continue;
            }
            List<Integer> list = map.getOrDefault( word, new ArrayList<>() );
            list.add( i );
            map.put( word, list );
        }
        List<Integer> l1 = map.get( word1 );
        List<Integer> l2 = map.get( word2 );
        int min = Integer.MAX_VALUE;
        for (Integer j : l1) {
            for (Integer k : l2) {
                min = Math.min( min, Math.abs( j-k ) );
            }
        }
        return min;
    }
}
