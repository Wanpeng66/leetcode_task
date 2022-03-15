package com.wp;

import java.util.Map;

/**
 * @author: wp
 * @Title: task_14 14. 最长公共前缀
 * @Description: https://leetcode-cn.com/problems/longest-common-prefix/
 * @date 2022/3/15 16:14
 */
public class task_14 {
    public static void main( String[] args ) {
        String[] strs = {"ab","a"};
        System.out.println( longestCommonPrefix( strs ) );
    }

    public static String longestCommonPrefix(String[] strs) {
        Trie trie = new Trie();
        for (String str : strs) {
            if ("".equals( str )) {
                return "";
            }
            trie.addStr( str );
        }
        Trie.TrieNode root = trie.root;
        StringBuilder prefix = new StringBuilder("");
        while (root.map.keySet().size()==1 && !root.isEndChar()) {
            Character key = root.getMap().keySet().toArray( new Character[1] )[0];
            prefix.append( key );
            root = root.getMap().get(key);
        }
        return prefix.toString();

    }
}
