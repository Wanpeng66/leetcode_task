package com.wp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wp
 * @Title: task_804  804. 唯一摩尔斯密码词
 * @Description: https://leetcode-cn.com/problems/unique-morse-code-words/
 * @date 2022/4/10 15:13
 */
public class task_804 {
    static String[] array = {".-","-...","-.-.","-..",".","..-.","--."
            ,"....","..",".---","-.-",".-..","--","-.","---",
            ".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> res = new HashSet<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char aChar : chars) {
                sb.append( array[aChar-'a'] );
            }
            res.add( sb.toString() );
        }
        return res.size();
    }
}
