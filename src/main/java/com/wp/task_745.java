package com.wp;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONArray;

import java.util.*;

/**
 * @author: wp
 * @Title: task_745  745. 前缀和后缀搜索
 * @Description: https://leetcode.cn/problems/prefix-and-suffix-search/
 * @date 2022/7/14 10:02
 */
public class task_745 {
    public static void main( String[] args ) {
        String str = FileUtil.readString( "C:\\Users\\plantdata-nb-0001\\Desktop\\test.txt", "utf-8" );
        JSONArray array = JSONArray.parseArray( str );
        String[] words = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            String val = array.getString( i );
            words[i] = val;
        }
        WordFilter wordFilter = new WordFilter( words );
        System.out.println( wordFilter.f( "f","f" ) );
    }
    static class WordFilter {
        Map<String, Set<Integer>> prefixMap;
        Map<String, Set<Integer>> suffixMap;
        Map<String,Integer> cache;
        public WordFilter(String[] words) {
            cache = new HashMap<>();
            prefixMap = new HashMap<>();
            suffixMap = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                char[] chars = word.toCharArray();
                int left = 0,right = 0;
                while (left< chars.length) {
                    if (right< chars.length) {
                        sb.append( chars[right++] );
                        setKey( sb, i, prefixMap );
                    }else{
                        if (left != 0) {
                            sb.deleteCharAt( 0 );
                        }
                        setKey( sb, i,suffixMap );
                        left++;
                    }
                }
                sb.setLength( 0 );
            }
        }

        private void setKey( StringBuilder sb, Integer offset, Map<String, Set<Integer>> suffixMap ) {
            String key = sb.toString();
            Set<Integer> set = suffixMap.getOrDefault( key, new HashSet<>() );
            set.add( offset );
            suffixMap.putIfAbsent( key,set );
        }


        public int f(String pref, String suff) {
            String key = pref + "+" + suff;
            if (cache.containsKey( key )) {
                return cache.get( key );
            }
            Set<Integer> prefixWords = prefixMap.getOrDefault( pref, new HashSet<>() );
            Set<Integer> suffixWords = suffixMap.getOrDefault( suff, new HashSet<>() );
            List<Integer> words = new ArrayList<>( prefixWords );
            words.retainAll( suffixWords );
            if(words.isEmpty()){
                return -1;
            }
            words.sort( Comparator.reverseOrder() );
            cache.put( key, words.get(0) );
            return words.get( 0 );
        }
    }
}
