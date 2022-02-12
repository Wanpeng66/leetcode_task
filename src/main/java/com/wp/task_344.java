package com.wp;

/**
 * @author: wp
 * @Title: task_344  344. 反转字符串
 * @Description: https://leetcode-cn.com/problems/reverse-string/
 * @date 2022/1/28 14:29
 */
public class task_344 {

    public static void main( String[] args ) {
        char[] s = {'h','a','n','n','a', 'H'};
        reverseString( s );
        System.out.println( s );
    }

    public static void reverseString(char[] s) {
        int size = s.length;
        for (int i = 0; i < size; i++) {
            if (i>=size-1-i) {
                break;
            }
            Character tmp = s[size-1-i];
            s[size-1-i] = s[i];
            s[i] = tmp;
        }
    }
}
