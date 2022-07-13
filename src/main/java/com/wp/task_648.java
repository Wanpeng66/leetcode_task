package com.wp;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author: wp
 * @Title: task_648  648. 单词替换
 * @Description: https://leetcode.cn/problems/replace-words/
 * @date 2022/7/7 17:52
 */
public class task_648 {
    public static void main( String[] args ) {
        System.out.println( replaceWords( Arrays.asList( "cat", "bat", "rat" ), "the cattle was rattled by the battery" ) );
    }

    public static String replaceWords( List<String> dictionary, String sentence) {
        StringBuilder buffer = new StringBuilder();
        String[] words = sentence.split( " " );
        for (String word : words) {
            String tmp = word;
            for (String dic : dictionary) {
                if (word.startsWith( dic ) && dic.length()<tmp.length()) {
                    tmp = dic;
                }
            }
            buffer.append( tmp ).append( " " );
        }
        return buffer.toString().trim();
    }
}
