package com.wp;

import java.util.Stack;

/**
 * @author: wp
 * @Title: task_844  844. 比较含退格的字符串
 * @Description:  https://leetcode-cn.com/problems/backspace-string-compare/
 * @date 2022/1/25 15:09
 */
public class task_844 {

    public static void main( String[] args ) {
        String s = "y#fo##f";
        String t = "y#f#o##f";
        System.out.println( backspaceCompare( s, t ) );
    }

    public static boolean backspaceCompare( String s, String t ) {
        String s1 = processStr( s );
        String s2 = processStr( t );
        return s1.equals( s2 );
    }

    public static String processStr( String s ) {
        char[] sArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : sArray) {
            if (c=='#' && !stack.isEmpty()) {
                stack.pop();
            }else if(c!='#'){
                stack.push( c );
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append( stack.pop() );
        }
        return sb.toString();
    }
}
