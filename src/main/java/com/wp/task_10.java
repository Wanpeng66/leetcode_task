package com.wp;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_10  10. 正则表达式匹配
 * @Description: https://leetcode-cn.com/problems/regular-expression-matching/
 * @date 2022/3/7 14:02
 */
public class task_10 {

    public static void main( String[] args ) {
        /*String s = "aabcbcbcaccbcaabc";
        String p = ".*a*aa*.*b*.c*.*a*";*/
        s = "aab";
        p = "c*a*b";
        System.out.println( isMatch( s, p ) );
    }


    static String s;
    static String p;
    static int[][] memo;
    static int Y = 1, N = -1, EMPTY = 0;

    public static boolean isMatch( String s, String p ) {
        memo = new int[s.length() + 1][p.length() + 1];
        return isMatch(0, 0) == Y;
    }

    public static int isMatch(int sIdx, int pIdx) {
        if (sIdx > s.length() || pIdx > p.length()) {
            return N;
        }
        if (memo[sIdx][pIdx] != EMPTY) {
            return memo[sIdx][pIdx];
        }
        if (sIdx == s.length() && pIdx == p.length()) {
            memo[sIdx][pIdx] = Y;
            return memo[sIdx][pIdx];
        }
        if (pIdx == p.length() && sIdx < s.length()) {
            memo[sIdx][pIdx] = N;
            return memo[sIdx][pIdx];
        }

        if (pIdx + 1 < p.length() && p.charAt(pIdx + 1) == '*') {
            if (p.charAt(pIdx) == '.') {
                for (int i = sIdx; i <= s.length(); i++) {
                    if (Y == isMatch(i, pIdx + 2)) {
                        memo[sIdx][pIdx] = Y;
                        return memo[sIdx][pIdx];
                    }
                }
            } else {
                for (int i = sIdx; i <= s.length(); i++) {
                    if (Y == isMatch(i, pIdx + 2)) {
                        memo[sIdx][pIdx] = Y;
                        return memo[sIdx][pIdx];
                    }
                    if (i < s.length() && s.charAt(i) != p.charAt(pIdx)) {
                        break;
                    }
                }
            }
            memo[sIdx][pIdx] = N;
            return memo[sIdx][pIdx];
        }

        if (pIdx < p.length()) {
            if ((p.charAt(pIdx) == '.') || (sIdx < s.length() && s.charAt(sIdx) == p.charAt(pIdx))) {
                memo[sIdx][pIdx] = isMatch(sIdx + 1, pIdx + 1);
                return memo[sIdx][pIdx];
            }
        }
        memo[sIdx][pIdx] = N;
        return memo[sIdx][pIdx];
    }


}
