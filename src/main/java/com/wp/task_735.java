package com.wp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: task_735  735. 行星碰撞
 * @Description: https://leetcode.cn/problems/asteroid-collision/
 * @date 2022/7/13 9:56
 */
public class task_735 {
    public static void main( String[] args ) {
        int[] asteroids = {-2,-1,1,2};
        int[] ints = asteroidCollision( asteroids );
        System.out.println( ints );
    }
    public static int[] asteroidCollision(int[] asteroids) {
       Deque<Integer> queue = new LinkedList<>();
        for (Integer asteroid : asteroids) {
            while (! queue.isEmpty()) {
                Integer peek = queue.peekLast();
                if (isOk( asteroid, peek )) break;
                asteroid = alive( asteroid, queue.removeLast() );
                if (null==asteroid) {
                    break;
                }
            }
            if (null!=asteroid) {
                queue.addLast( asteroid );
            }
        }
        int[] ans = new int[queue.size()];
        int offset = 0;
        for (Integer val : queue) {
            ans[offset++] = val;
        }
        return ans;
    }
    public static boolean isOk(Integer i1,Integer i2){
        if ( i1<0&&i2>0 ) {
            return false;
        }
        return true;
    }

    public static Integer alive(Integer i1,Integer i2){
        int i = Math.abs( i1 ) - Math.abs( i2 );
        return i==0?null:i>0?i1:i2;
    }
}
