package com.wp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: wp
 * @Title: task_682  682. 棒球比赛
 * @Description: https://leetcode-cn.com/problems/baseball-game/
 * @date 2022/3/26 8:48
 */
public class task_682 {
    public static void main( String[] args ) {
        String[] ops = {"5","2","C","D","+"};
        System.out.println( calPoints( ops ) );
    }

    public static int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if (op.equals( "+" )) {
                Integer first = stack.pop();
                Integer second = stack.pop();
                stack.push( second );
                stack.push( first );
                stack.push( first+second );
            } else if (op.equals( "D" )) {
                stack.push( stack.peek()*2 );
            } else if (op.equals( "C" )) {
                stack.pop();
            }else{
                stack.push( Integer.parseInt( op ) );
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum+=stack.pop();
        }
        return  sum;
    }
}
