package com.wp;

/**
 * @author: wp
 * @Title: task_796  796. 旋转字符串
 * @Description: https://leetcode-cn.com/problems/rotate-string/
 * @date 2022/4/8 10:46
 */
public class task_796 {
    public boolean rotateString(String s, String goal) {
        int length = s.length();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < length; i++) {
            char first = sb.charAt( 0 );
            sb = sb.deleteCharAt( 0 );
            sb.append( first );
            if (sb.toString().equals( goal )) {
                return true;
            }
        }
        return false;
    }
}
