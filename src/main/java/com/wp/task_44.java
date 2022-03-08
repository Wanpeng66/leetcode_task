package com.wp;

import java.util.Arrays;
import java.util.List;

/**
 * @author: wp
 * @Title: task_44 44. 通配符匹配
 * @Description: https://leetcode-cn.com/problems/wildcard-matching/
 * @date 2022/3/7 17:20
 */
public class task_44 {


    public static void main( String[] args ) {
        String s = "abcabczzzde";
        String p = "*abc???de*";
        /*String s = "bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab";
        String p = "b*b*ab**ba*b**b***bba";*/
        long start = System.currentTimeMillis();
        boolean match = isMatch( s, p );
        long end = System.currentTimeMillis();
        System.out.println( end-start );
        System.out.println(match);
    }
    static boolean[][] flag;
    public static boolean isMatch(String s, String p) {
        if (p.equals( "" ) && s.equals( "" )) {
            return true;
        }
        if (p.equals( "" )) {
            return false;
        }
        if (s.equals( "" )) {
            return !p.matches( ".*[a-z?]+.*" );
        }
        char[] sArray = s.toCharArray();
        char[] pArray = p.toCharArray();
        boolean[] res = {false};
        flag = new boolean[sArray.length+1][pArray.length+1];
        isMatch(sArray,0,pArray,0,res);
        return res[0];
    }
    private static void isMatch( char[] sArray, int p, char[] pArray, int q, boolean[] res ) {
        if (p<=sArray.length && q<=pArray.length && flag[p][q]) {
            return;
        }
        if (res[0]) {
            return;
        }
        flag[p][q] = true;
        if (p==sArray.length && q==pArray.length) {
            res[0] = true;
            return;
        }
        if (p<sArray.length && q<pArray.length && (sArray[p]==pArray[q] || pArray[q] == '?')) {
            isMatch( sArray,p+1,pArray,q+1,res );
        } else if (q<pArray.length && pArray[q]=='*') {
            for (int i = 0; i <= sArray.length - p ; i++) {
                isMatch( sArray,p+i,pArray,q+1,res );
                if (res[0]) {
                    break;
                }
            }
        }
    }

}
