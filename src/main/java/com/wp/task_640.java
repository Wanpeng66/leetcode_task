package com.wp;

import java.util.Stack;

/**
 * @author: wp
 * @Title: task_640  640. 求解方程
 * @Description: https://leetcode.cn/problems/solve-the-equation/
 * @date 2022/8/10 16:28
 */
public class task_640 {
    public static void main( String[] args ) {
        String equation = "-x=-1";
        System.out.println( solveEquation( equation ) );
    }
    public static String solveEquation(String equation) {
        String[] split = equation.split( "=" );
        Task left = new Task();
        String leftStr = split[0];
        char[] chars = leftStr.toCharArray();
        StringBuilder sb = new StringBuilder();
        process( left, chars, sb );
        sb.setLength( 0 );
        Task right = new Task();
        String rightStr = split[1];
        chars = rightStr.toCharArray();
        process( right, chars, sb );
        right.setNum( right.getNum() - left.getNum() );
        left.setNum( 0 );
        left.setCoefficient( left.getCoefficient() - right.getCoefficient() );
        right.setCoefficient( 0 );
        if (left.getCoefficient()==0 && right.getNum()==0) {
            return "Infinite solutions";
        } else if(left.getCoefficient()!=0) {
            return "x="+right.getNum() / left.getCoefficient();
        }else{
            return "No solution";
        }
    }

    private static void process( Task task, char[] chars, StringBuilder sb ) {
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c=='x') {
                int num = 1;
                if (sb.length()>0) {
                    if (sb.toString().equals( "-" )) {
                        num = -1;
                    }else{
                        num = Integer.parseInt( sb.toString() );
                    }
                    sb.setLength( 0 );
                }
                task.setCoefficient( task.getCoefficient()+num );
            } else if (c=='+' || c=='-') {
                if (sb.length()>0) {
                    task.setNum( task.getNum()+Integer.parseInt( sb.toString() ) );
                    sb.setLength( 0 );
                }
                if (c=='-') {
                    sb.append( c );
                }
            }else{
                sb.append( c );
            }
        }
        if (sb.length()>0) {
            task.setNum( task.getNum()+Integer.parseInt( sb.toString() ) );
        }
    }

    static class Task{
        private int coefficient;
        private int num;

        public int getCoefficient() {
            return coefficient;
        }

        public void setCoefficient( int coefficient ) {
            this.coefficient = coefficient;
        }

        public int getNum() {
            return num;
        }

        public void setNum( int num ) {
            this.num = num;
        }
    }
}
