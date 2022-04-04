package com.wp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_5302 5302. 加密解密字符串
 * @Description: https://leetcode-cn.com/contest/weekly-contest-287/problems/encrypt-and-decrypt-strings/
 * @date 2022/4/3 13:35
 */
public class task_5302 {
    public static void main( String[] args ) {
        char[] keys = {'a','b','c','z'};
        String[] values = {"aa","bb","cc","zz"};
        String[] dictionary = {"aa","aaa","aaaa","aaaaa","aaaaaa"};
        Encrypter encrypter = new Encrypter( keys, values, dictionary );
        System.out.println( encrypter.decrypt( "aa" ) );
    }
}
class Encrypter {
    Map<Character,String> keys2Values;
    Map<String,List<Character>> values2Keys;
    Trie trie = new Trie();
    static Map<String,Integer> hasDone = new HashMap<>();
    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        keys2Values = new HashMap<>();
        values2Keys = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            keys2Values.put( keys[i],values[i] );
            List<Character> list = values2Keys.getOrDefault( values[i], new ArrayList<>() );
            list.add( keys[i] );
            values2Keys.put( values[i], list );
        }
        for (String s : dictionary) {
            trie.addStr( s );
        }
    }

    public String encrypt(String word) {
        char[] chars = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append( keys2Values.get( aChar ) );
        }
        return sb.toString();
    }

    public int decrypt(String word) {
        if (hasDone.containsKey( word )) {
            return hasDone.get( word );
        }
        List[] lists = new List[word.length()/2];
        int i = 0;
        while (!"".equals( word )) {
            String str = word.substring( 0,2 );
            word = word.substring( 2 );
            List<Character> list = values2Keys.get( str );
            if (null==list) {
                return 0;
            }
            lists[i++] = list;
        }
        int[] num = {0};
        dfs(lists,0,lists.length,new StringBuilder(), trie.root,num);
        hasDone.put( word, num[0] );
        return num[0];
    }

    private void dfs( List[] lists, int i, int max, StringBuilder stringBuilder, Trie.TrieNode node, int[] num ) {
        if (i>=max) {
            if (trie.findStr( stringBuilder.toString() )) {
                num[0]++;
            }
            return;
        }
        List<Character> list = lists[i];
        for (Character aChar : list) {
            if (node.map.containsKey( aChar )) {
                stringBuilder.append( aChar );
                int offset = stringBuilder.length() - 1;
                dfs( lists,i+1,max,stringBuilder,node.map.get( aChar ),num );
                stringBuilder.deleteCharAt( offset );
            }
        }
    }
}