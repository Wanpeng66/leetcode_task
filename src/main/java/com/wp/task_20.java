package com.wp;

import java.util.Stack;

/**
 * @author: wp
 * @Title: task_20 20. 有效的括号
 * @Description: https://leetcode-cn.com/problems/valid-parentheses/
 * @date 2022/1/25 10:10
 */
public class task_20 {
    public static void main( String[] args ) {
        String s = "()[]{}}";
        System.out.println( isValid( s ) );
    }
    public static boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : charArray) {
            if (c=='}' || c==')' || c==']') {
                if (stack.isEmpty()) {
                    return false;
                }
                Character leftChar = stack.pop();
                if (null==leftChar || (c=='}' && leftChar!='{') || (c==')' && leftChar!='(') || (c==']') && leftChar!='[') {
                    return false;
                }
            }else{
                stack.push( c );
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}
