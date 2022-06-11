package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_473 473. 火柴拼正方形
 * @Description: https://leetcode.cn/problems/matchsticks-to-square/
 * @date 2022/6/1 14:39
 */
public class task_473 {
    public static void main( String[] args ) {
        int[] matchsticks = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,102};
        System.out.println( makesquare( matchsticks ) );
    }
    public static boolean makesquare(int[] matchsticks) {
        int totalLen = Arrays.stream(matchsticks).sum();
        if (totalLen % 4 != 0) return false;
        int side = totalLen / 4;
        Arrays.sort(matchsticks);
        boolean[] flag = {false};
        int[] sides = new int[4];
        back_trace(matchsticks, matchsticks.length-1, sides, flag, side);
        return flag[0];
    }

    private static void back_trace( int[] matchsticks, int offset, int[] sides, boolean[] flag, int side ) {
        if (flag[0])  return;
        if (offset<0) {
            flag[0] = true;
            for (int i = 1; i < sides.length; i++) {
                if (sides[i]!=sides[i-1]) {
                    flag[0] = false;
                    break;
                }
            }
            return;
        }
        int value= matchsticks[offset];
        if (sides[0]+value<=side) {
            sides[0]+= value;
            back_trace( matchsticks, offset-1, sides, flag, side );
            sides[0]-= value;
        }
        if (sides[1]+value<=side) {
            sides[1]+= value;
            back_trace( matchsticks, offset-1, sides, flag, side );
            sides[1]-= value;
        }
        if (sides[2]+value<=side) {
            sides[2]+= value;
            back_trace( matchsticks, offset-1, sides, flag, side );
            sides[2]-= value;
        }
        if (sides[3]+value<=side) {
            sides[3]+= value;
            back_trace( matchsticks, offset-1, sides, flag, side );
            sides[3]-= value;
        }
    }
}
