package com.wp;

import java.util.*;
import java.util.stream.Collectors;

public class task_1233 {
    public static void main(String[] args) {
        String[] folder = {"/ah/al/am","/ah/al"};
        List<String> list = removeSubfolders(folder);
        System.out.println(list);
    }
    public static List<String> removeSubfolders(String[] folder) {
        List<String> res = new ArrayList<>();
        TrieNode root = new TrieNode("/");
        List<String> folderList = Arrays.stream(folder).sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        for (String path : folderList) {
            boolean isSubFolder = false;
            TrieNode node = root;
            String[] strings = path.split("/");
            for (int i = 1; i < strings.length; i++) {
                if (!node.getMap().containsKey(strings[i])) {
                    TrieNode newNode = new TrieNode(strings[i], i == strings.length - 1);
                    node.getMap().put(strings[i], newNode);
                    node = newNode;
                }else{
                    TrieNode tmp = node.getMap().get(strings[i]);
                    if (tmp.isEndChar) {
                        isSubFolder = true;
                        break;
                    }else {
                        node = tmp;
                    }
                }
            }
            if (!isSubFolder) {
                res.add(path);
            }
        }

        return res;
    }
}
class TrieNode{
    String val;
    Map<String, TrieNode> map;
    boolean isEndChar;

    public TrieNode( String val ) {
        this.val = val;
        this.isEndChar = false;
        map = new HashMap<>();
    }

    public TrieNode( String val, boolean isEndChar ) {
        this.val = val;
        this.isEndChar = isEndChar;
        map = new HashMap<>();
    }

    public String getVal() {
        return val;
    }
    public Map<String, TrieNode> getMap() {
        return map;
    }

    public boolean isEndChar() {
        return isEndChar;
    }

    public void setEndChar( boolean endChar ) {
        isEndChar = endChar;
    }
}
