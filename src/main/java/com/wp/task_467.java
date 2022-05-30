package com.wp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wp
 * @Title: task_467  467. 环绕字符串中唯一的子字符串
 * @Description: https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
 * @date 2022/5/25 14:52
 */
public class task_467 {
    public static void main( String[] args ) {
        System.out.println( findSubstringInWraproundString( "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" ) );
    }

    public static int findSubstringInWraproundString(String p) {
        int num = 0;
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            sb.setLength( 0 );
            int offset = i;
            for (; offset < p.length();offset++ ) {
                sb.append( p.charAt( offset ) );
                String tmp = sb.toString();
                if (!set.contains( tmp )) {
                    char[] chars = tmp.toCharArray();
                    if (tmp.length()==1 && !set.contains( tmp )) {
                        num++;
                        set.add( tmp );
                    }else if (!((chars[tmp.length()-2]=='z' && chars[tmp.length()-1]=='a')
                            || chars[tmp.length()-1]-chars[tmp.length()-2]==1)) {
                        break;
                    }else{
                        num++;
                        set.add( tmp );
                    }
                }
            }
        }
        return num;
    }

    private static int method1( String p ) {
        int num = 0;
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            sb.setLength( 0 );
            int right = i;
            for (int j = 0; j <= right; j++) {
                sb.append( p.charAt( j ) );
            }
            while (true) {
                String tmp = sb.toString();
                if (!set.contains( tmp )) {
                    set.add( tmp );
                    char[] chars = tmp.toCharArray();
                    boolean flag = true;
                    for (int k = 0; k < chars.length-1; k++) {
                        if (!((chars[k]=='z' && chars[k+1]=='a') || chars[k+1]-chars[k]==1)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        num++;
                    }
                }
                sb.deleteCharAt( 0 );
                ++right;
                if (right< p.length()) {
                    sb.append( p.charAt( right ) );
                }else{
                    break;
                }
            }

        }
        return num;
    }
}
