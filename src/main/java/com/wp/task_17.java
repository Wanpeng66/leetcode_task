package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_17  17. 电话号码的字母组合
 * @Description: https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * @date 2022/2/21 14:33
 */
public class task_17 {
    static String[][] array = {{},{},{"a","b","c"},{"d","e","f"},{"g","h","i"},{"j","k","l"},
            {"m","n","o"},{"p","q","r","s"},{"t","u","v"},{"w","x","y","z"}};

    public static void main( String[] args ) {
        String digits = "23";
        System.out.println( letterCombinations( digits ) );
    }

    public static List<String> letterCombinations( String digits) {
        if (null==digits || "".equals( digits )) {
            return new ArrayList<>();
        }
        String[] split = digits.split( "" );
        List<String> list = new ArrayList<>();
        String[] res = new String[split.length];
        getStr( split, 0, list, res );
        return list;
    }

    private static void getStr( String[] split, int i, List<String> list, String[] res ) {
        if (i>=split.length) {
            list.add( String.join( "",res ) );
            return;
        }
        String num = split[i];
        String[] nums = array[Integer.parseInt( num )];
        for (int j = 0; j < nums.length; j++) {
            res[i] = nums[j];
            getStr( split, i+1, list, res );
        }

    }
}
