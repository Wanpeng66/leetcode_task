package com.wp;

/**
 * @author: wp
 * @Title: task_10  10. 正则表达式匹配
 * @Description: https://leetcode-cn.com/problems/regular-expression-matching/
 * @date 2022/3/7 14:02
 */
public class task_10 {

    public static void main( String[] args ) {
        String s = "aabcbcbcaccbcaabc";
        String p = ".*a*aa*.*b*.c*.*a*";
        System.out.println( isMatch( s, p ) );
    }


    public static boolean isMatch(String s, String p) {
        char[] targetArray = s.toCharArray();
        char[] patternArray = p.toCharArray();
        int tIndex = 0;
        int pIndex = 0;
        return recursionMatch(targetArray, tIndex, patternArray, pIndex);
    }

    public static boolean isMatchChar(char[] s, int s1, char[] p, int p1) {
        if(p1 >= p.length) return s1 >= s.length;
        boolean match = s1 < s.length && ((s[s1] == p[p1]) || p[p1] == '.');
        if(p.length - p1 >= 2 && p[p1 + 1] == '*')
            return isMatchChar(s, s1, p, p1 + 2) || (match && isMatchChar(s, s1 + 1, p, p1));
        return match && isMatchChar(s, s1 + 1, p, p1 + 1);
    }


    private static boolean recursionMatch( char[] targetArray, int tIndex, char[] patternArray, int pIndex ) {
        if (pIndex >= patternArray.length && tIndex>=targetArray.length) {
            return true;
        }

        if (pIndex+1<patternArray.length && patternArray[pIndex+1]=='*') {
            if (tIndex>=targetArray.length) {
                return false;
            }else if (targetArray[tIndex]==patternArray[pIndex] || patternArray[pIndex]=='.') {
                boolean res = true;
                res = recursionMatch( targetArray,tIndex+1,patternArray,pIndex );
                return res || recursionMatch( targetArray,tIndex,patternArray,pIndex+2 );
            }else{
                return recursionMatch( targetArray, tIndex, patternArray,pIndex+2 );
            }
        }else{
            if (tIndex>=targetArray.length || pIndex>=patternArray.length) {
                return false;
            }else if (targetArray[tIndex]==patternArray[pIndex] || patternArray[pIndex]=='.') {
                return recursionMatch( targetArray,tIndex+1,patternArray,pIndex+1 );
            }else{
                return false;
            }
        }
    }

}
