package com.wp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_953 953. 验证外星语词典
 * @Description: https://leetcode.cn/problems/verifying-an-alien-dictionary/
 * @date 2022/5/17 14:55
 */
public class task_953 {
    public static void main( String[] args ) {
        String[] words = {"apple","app"};
        String order = "abcdefghijklmnopqrstuvwxyz";
        System.out.println( isAlienSorted( words, order ) );
    }
    public static boolean isAlienSorted(String[] words, String order) {
        Map<Character,Integer> map = new HashMap<>(order.length()+1);
        map.put( '∅',0 );
        char[] chars = order.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put( chars[i], i+1 );
        }
        for (int i = 1; i < words.length; i++) {
            String s1 = words[i - 1];
            String s2 = words[i];
            int size = Math.max( s1.length(), s2.length() );
            for (int j = 0; j < size; j++) {
                Integer n1 = j >= s1.length() ? map.get( '∅' ) : map.get( s1.charAt( j ) );
                Integer n2 = j >= s2.length() ? map.get( '∅' ) : map.get( s2.charAt( j ) );
                if (n1<n2) {
                    break;
                }
                if (n1>n2) {
                    return false;
                }
            }
        }
        return true;
    }
}
