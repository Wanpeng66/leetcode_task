package com.wp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: wp
 * @Title: task_1021  1021. 删除最外层的括号
 * @Description: https://leetcode.cn/problems/remove-outermost-parentheses/
 * @date 2022/5/28 18:17
 */
public class task_1021 {
    public static void main( String[] args ) {
        System.out.println( removeOuterParentheses( "(()())(())(()(()))" ) );
    }
    public static String removeOuterParentheses(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int leftNum = 0,rightNum = 0;
        for (char aChar : chars) {
            if (aChar=='(') {
                if (leftNum!=0) {
                    sb.append( aChar );
                }
                leftNum++;
            } else if (aChar==')' ) {
                rightNum++;
                if (rightNum==leftNum) {
                    leftNum = rightNum = 0;
                }else{
                    sb.append( aChar );
                }
            }
        }
        return sb.toString();
    }
}
