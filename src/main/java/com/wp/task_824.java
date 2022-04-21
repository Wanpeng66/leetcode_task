package com.wp;

import java.util.Arrays;
import java.util.List;

/**
 * @author: wp
 * @Title: task_824  824. 山羊拉丁文
 * @Description: https://leetcode-cn.com/problems/goat-latin/
 * @date 2022/4/21 12:09
 */
public class task_824 {
    public static void main( String[] args ) {
        System.out.println( toGoatLatin("I speak Goat Latin") );
    }

    static List<Character> list = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
    public static String toGoatLatin(String sentence) {
        String[] words = sentence.split( " " );
        String suffix = "";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            suffix = suffix.concat( "a" );
            char c = word.charAt( 0 );
            if (list.contains( c )) {
                word = word.concat( "ma" ).concat( suffix );
            }else{
                word = word.substring( 1 ).concat( word.substring( 0, 1 ) ).concat( "ma" ).concat( suffix );
            }
            words[i] = word;
        }
        return String.join( " ", words );
    }
}
