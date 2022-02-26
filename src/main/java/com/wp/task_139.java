package com.wp;

import javax.lang.model.element.VariableElement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_139  139. 单词拆分
 * @Description: https://leetcode-cn.com/problems/word-break/
 * @date 2022/2/19 16:25
 */
public class task_139 {


    public static void main( String[] args ) {
        String s = "aaaaaaa";
        String[] str = {"aaaa","aaa"};
        List<String> wordDict = Arrays.asList( str );
        System.out.println( wordBreak( s, wordDict ) );
    }

    static Map<Integer,Boolean> check = new HashMap<>();
    public static boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String str : wordDict) {
            trie.addStr( str );
        }
        Map<String,Boolean> res = new HashMap<>();
        return wordBreak( s, wordDict, trie ,res );
    }

    private static boolean wordBreak( String s, int start, Trie trie ) {
        if (check.containsKey( start )){
            return check.get( start );
        }
        if (start >= s.length()){
            return true;
        }
        Trie.TrieNode p = trie.root;
        for (; start < s.length(); start++) {
            char aChar = s.charAt(start) ;
            if (!p.getMap().containsKey( aChar )) {
                return false;
            }
            p = p.getMap().get(aChar);
            if (p.isEndChar) {
                if (wordBreak(s, start + 1, trie)){
                    return true;
                }
                check.put( start, false );
            }
        }
        return false;
    }


    private static boolean wordBreak( String s, List<String> wordDict, Trie trie, Map<String, Boolean> res ) {
        if (res.containsKey( s )) {
            return res.get( s );
        }
        boolean flag = false;
        for (String str : wordDict) {
            if (s.length()<str.length()) {
                continue;
            }
            if (str.equals( s )) {
                return true;
            }
            if (s.length() == str.length()) {
                continue;
            }
            String subStr = s.substring( 0, str.length() );
            if (trie.findStr(subStr)) {
                flag = flag || wordBreak( s.substring( str.length() ), wordDict, trie, res );
                if (flag) {
                    break;
                }
            }
        }
        res.put( s,flag );
        return flag;
    }


}
