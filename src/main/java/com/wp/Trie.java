package com.wp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: TrieNode
 * @Description:
 * @date 2022/2/19 16:26
 */
public class Trie {

    TrieNode root;
    long total = 0;

    public Trie(  ) {
        root = new TrieNode( '/' );
    }

    public void addStr( String str){
        char[] chars = str.toCharArray();
        TrieNode node = root;
        for (char aChar : chars) {
            Map<Character, TrieNode> map = node.getMap();
            if (map.containsKey( aChar )) {
                node = map.get( aChar );
            }else{
                node = new TrieNode( aChar );
                map.put( aChar, node );
            }
        }
        node.setEndChar( true );
        total++;
    }

    public boolean findStr(String str){
        char[] chars = str.toCharArray();
        TrieNode node = root;
        for (char aChar : chars) {
            Map<Character, TrieNode> map = node.getMap();
            if (map.containsKey( aChar )) {
                node  = map.get( aChar );
            }else{
                return false;
            }
        }
        return node.isEndChar;
    }

    class TrieNode{
        Character val;
        Map<Character,TrieNode> map;
        boolean isEndChar;

        public TrieNode( Character val ) {
            this.val = val;
            this.isEndChar = false;
            map = new HashMap<>();
        }

        public TrieNode( Character val, boolean isEndChar ) {
            this.val = val;
            this.isEndChar = isEndChar;
            map = new HashMap<>();
        }

        public Character getVal() {
            return val;
        }
        public Map<Character, TrieNode> getMap() {
            return map;
        }

        public boolean isEndChar() {
            return isEndChar;
        }

        public void setEndChar( boolean endChar ) {
            isEndChar = endChar;
        }
    }
}
