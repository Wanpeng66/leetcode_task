package com.wp;

/**
 * @author: wp
 * @Title: task_28 28. 实现 strStr()
 * @Description: https://leetcode-cn.com/problems/implement-strstr/
 * @date 2022/3/15 16:37
 */
public class task_28 {
    public static void main( String[] args ) {
        String haystack = "mississippi";
        String needle = "issipi";
        System.out.println( strStr( haystack, needle ) );
    }

    public static int strStr(String haystack, String needle) {
        if (needle.equals( "" )) {
            return 0;
        }
        if (needle.length()>haystack.length()) {
            return -1;
        }
        char[] pArray = haystack.toCharArray();
        char[] chars = needle.toCharArray();
        int offset = -1;
        boolean flag = true;
        for (int i = 0; i < pArray.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (i+j<pArray.length) {
                    if (pArray[i+j]!=chars[j]) {
                        flag = false;
                        break;
                    }
                    if (offset==-1) {
                        offset = i;
                    }
                }else{
                    flag = false;
                }
            }
            if (offset!=-1 && flag) {
                break;
            }
            offset = -1;
            flag = true;
        }
        return offset;
    }
}
