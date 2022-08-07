package com.wp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: wp
 * @Title: task_676  676. 实现一个魔法字典
 * @Description: https://leetcode.cn/problems/implement-magic-dictionary/
 * @date 2022/7/11 13:31
 */
public class task_676 {
    class MagicDictionary {
        Map<Integer, Set<String>> map;
        public MagicDictionary() {
            map = new HashMap<>();
        }

        public void buildDict(String[] dictionary) {
            for (String dic : dictionary) {
                Set<String> set = map.getOrDefault( dic.length(), new HashSet<>() );
                set.add( dic );
                map.putIfAbsent( dic.length(),set );
            }
        }

        public boolean search(String searchWord) {
            int length = searchWord.length();
            Set<String> set = map.get( length );
            if (null==set||set.isEmpty()) {
                return false;
            }
            for (String dic : set) {
                if (getDifferent(dic,searchWord)==1) {
                    return true;
                }
            }
            return false;
        }

        private int getDifferent( String dic, String searchWord ) {
            int different = 0;
            char[] a1 = dic.toCharArray();
            char[] a2 = searchWord.toCharArray();
            for (int i = 0; i < a1.length; i++) {
                if (a1[i]!=a2[i]) {
                    different++;
                }
            }
            return different;
        }
    }
}
