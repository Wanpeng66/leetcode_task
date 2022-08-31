package com.wp;

import java.util.Stack;

/**
 * @author: wp
 * @Title: task_946  946. 验证栈序列
 * @Description: https://leetcode.cn/problems/validate-stack-sequences/
 * @date 2022/8/31 10:23
 */
public class task_946 {
    public static void main( String[] args ) {
        int[] pushed = {0};
        int[] popped = {0};
        System.out.println( validateStackSequences( pushed, popped ) );
    }
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int push = 0,pop = 0;
        while (push<pushed.length) {
            while (pop<=popped.length && !stack.isEmpty() && stack.peek()==popped[pop]) {
                stack.pop();
                pop++;
            }
            stack.push( pushed[push++] );
        }
        while (!stack.isEmpty() && pop<=popped.length) {
            if (stack.pop()==popped[pop]) {
                pop++;
            }else{
                return false;
            }
        }
        return true;
    }
}
