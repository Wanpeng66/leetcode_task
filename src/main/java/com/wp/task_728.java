package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_728   728. 自除数
 * @Description: https://leetcode-cn.com/problems/self-dividing-numbers/
 * @date 2022/3/31 18:14
 */
public class task_728 {
    public static void main( String[] args ) {
        List<Integer> list = selfDividingNumbers( 1, 22 );
        System.out.println( list );
    }

    public static List<Integer> selfDividingNumbers( int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (; left <= right; left++) {
            if (juge(left)) {
                res.add( left );
            }
        }
        return res;
    }

    private static boolean juge( int left ) {
        char[] chars = String.valueOf( left ).toCharArray();
        for (char a : chars) {
            if (a=='0' || left % Character.getNumericValue( a ) != 0) {
                return false;
            }
        }
        return true;
    }
}
