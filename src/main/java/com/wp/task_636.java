package com.wp;

import java.sql.Statement;
import java.util.*;

/**
 * @author: wp
 * @Title: task_636  636. 函数的独占时间
 * @Description: https://leetcode.cn/problems/exclusive-time-of-functions/
 * @date 2022/8/7 13:11
 */
public class task_636 {
    public static void main( String[] args ) {
        List<String> logs = Arrays.asList( "0:start:0","1:start:2","1:end:5","0:end:6" );
        int n = 2;
        int[] ints = exclusiveTime( n, logs );
        System.out.println( ints );
    }
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Log> stack = new Stack<>();
        int ts = 0;
        for (String str : logs) {
            String[] split = str.split( ":" );
            Log log = new Log( Integer.parseInt( split[0] ),  Integer.parseInt( split[2] ) );
            if (!stack.isEmpty()) {
                Log peek = stack.peek();
                if (split[1].equalsIgnoreCase( "start" )) {
                    if (!stack.isEmpty()) {
                        ts = log.ts - peek.ts;
                        res[peek.id] += ts;
                        peek.ts = log.ts;
                    }
                    stack.push( log );
                }else{
                    peek = null;
                    Log pop = stack.pop();
                    if (!stack.isEmpty()) {
                        peek = stack.peek();
                    }
                    ts = log.ts - pop.ts + 1;
                    res[log.id] += ts;
                    if (null!=peek) {
                        peek.ts = log.ts + 1;
                    }
                }
            }else{
                stack.push( log );
            }
        }

        return res;
    }


    static class Log{
        public int id;
        public int ts;

        public Log( int id, int ts ) {
            this.id = id;
            this.ts = ts;
        }
    }
}
