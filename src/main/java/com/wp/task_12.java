package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_12  12. 整数转罗马数字
 * @Description: https://leetcode-cn.com/problems/integer-to-roman/
 * @date 2022/3/20 12:44
 */
public class task_12 {
    public static void main( String[] args ) {
        System.out.println( intToRoman( 58 ) );
    }

    static TreeMap<Integer,String> map = new TreeMap<>(Comparator.reverseOrder());
    static{
        map.put( 1,"I" );map.put( 5,"V" );
        map.put( 10,"X" );map.put( 50,"L" );
        map.put( 100,"C" );map.put( 500,"D" );
        map.put( 1000,"M" );map.put( 4,"IV" );
        map.put( 9,"IX" );map.put( 40,"XL" );
        map.put( 90,"XC" );map.put( 400,"CD" );
        map.put( 900,"CM" );
    }

    public static String intToRoman(int num) {
        if (map.containsKey( num )) {
            return map.get( num );
        }
        StringBuilder sb = new StringBuilder();
        Integer[] keys = map.keySet().toArray( new Integer[0] );
        big:while (true) {
            for (Integer key : keys) {
                if (num==0) {
                    break big;
                }
                if (num>=key) {
                    num -= key;
                    sb.append( map.get( key ) );
                    break;
                }
            }
        }
        return sb.toString();
    }
}
