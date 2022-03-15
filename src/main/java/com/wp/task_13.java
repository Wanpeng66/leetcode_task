package com.wp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_13 13. 罗马数字转整数
 * @Description: https://leetcode-cn.com/problems/roman-to-integer/
 * @date 2022/3/15 15:46
 */
public class task_13 {
    public static void main( String[] args ) {
        String s = "DCXXI";
        System.out.println( romanToInt( s ) );
    }

    public static int romanToInt(String s) {
        Map<String,Integer> map = new HashMap<>();
        map.put( "I",1 );map.put( "V",5 );
        map.put( "X",10 );map.put( "L",50 );
        map.put( "C",100 );map.put( "D",500 );
        map.put( "M",1000 );map.put( "IV",4 );
        map.put( "IX",9 );map.put( "XL",40 );
        map.put( "XC",90 );map.put( "CD",400 );
        map.put( "CM",900 );
        int sum = 0;
        for (int i = 0; i < s.length();) {
            if (i+2<=s.length()) {
                String sub = s.substring( i, i + 2 );
                if (map.containsKey( sub )) {
                    sum += map.get( sub );
                    i += 2;
                    continue;
                }
            }
            String sub = s.substring( i, i + 1 );
            sum += map.get( sub );
            i++;
        }
        return sum;
    }
}
