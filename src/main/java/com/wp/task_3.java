package com.wp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wp
 * @Title: task_3  3. 无重复字符的最长子串
 * @Description: https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @date 2022/4/10 18:38
 */
public class task_3 {

    public static void main( String[] args ) {
        System.out.println( lengthOfLongestSubstring( " " ) );
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left = 0, right = 0;
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        for (; right < chars.length; right++) {
            while (set.contains( chars[right] )) {
                int size = set.size();
                if (size>max) {
                    max = size;
                }
                set.remove( chars[left++] );
            }
            set.add( chars[right] );
        }
        if (set.size()>max) {
            max = set.size();
        }
        return max;
    }
}
