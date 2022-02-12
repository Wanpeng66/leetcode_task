package com.wp;
import java.util.Stack;

/**
 * @author: wp
 * @Title: task_224 224. 基本计算器
 * @Description: https://leetcode-cn.com/problems/basic-calculator/
 * @date 2022/1/27 17:06
 */
public class task_224 {

    public static void main( String[] args ) {
        String tmp = "- (3 + (4 + 5))";
        System.out.println(calculate( tmp ));
    }

    public static int calculate2(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        // sign 代表正负
        int sign = 1, res = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int cur = ch - '0';
                while (i + 1 < length && Character.isDigit(s.charAt(i + 1)))
                    cur = cur * 10 + s.charAt(++i) - '0';
                res = res + sign * cur;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
            } else if (ch == ')') {
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }

    public static int calculate(String s) {
        String[] split = s.split( "" );
        Stack<Integer> numStack = new Stack<>();
        Stack<String> stack = new Stack<>();
        String str = null;
        StringBuilder sb = new StringBuilder();
        for (String c : split) {
            if (c.equals( " " )) {
                continue;
            }
            if (!(c.equals( "(" )||c.equals( ")" )||c.equals( "+" )||c.equals( "-" ))) {
                sb.append( c );
            }else{
                String tmp = sb.toString();
                if (!(null==tmp || "".equals( tmp ))) {
                    numStack.push( Integer.valueOf( tmp ) );
                    sb.setLength( 0 );
                }
                if (c.equals( "(" )  ) {
                    stack.push( c );
                }else{
                    while (!stack.isEmpty()) {
                        String pop = stack.pop();
                        if (pop.equals( "(" )) {
                            if (!c.equals( ")" )) {
                                stack.push( "(" );
                            }
                            break;
                        }
                        Integer one = numStack.pop();
                        Integer two = null;
                        if (pop.equals( "-1" )) {
                            two = 0;
                        }else{
                            two = numStack.pop();
                        }
                        Integer result;
                        if (pop.equals( "+" )) {
                            result = one + two;
                        }else{
                            result = two - one;
                        }
                        numStack.push( result );
                    }
                    if (!c.equals( ")" )) {
                        if (c.equals( "-" )&&(null==str || str.equals( "(" ))) {
                            stack.push( "-1" );
                        }else{
                            stack.push( c );
                        }

                    }
                }
            }
            str = c;
        }
        String tmp = sb.toString();
        if (!(null==tmp || "".equals( tmp ))) {
            numStack.push( Integer.valueOf( tmp ) );
            sb.setLength( 0 );
        }
        if (stack.isEmpty()) {
            return numStack.pop();
        }
        while (!stack.isEmpty()){
            String pop = stack.pop();
            if (pop.equals( "-1" )&&stack.isEmpty()&&numStack.size()==1) {
                return -numStack.pop();
            }
            Integer one = numStack.pop();
            Integer two = numStack.pop();
            Integer result = null;
            if (pop.equals( "+" )) {
                result = one + two;
            }else{
                result = two - one;
            }
            numStack.push( result );
        }
        return numStack.pop();
    }
}
