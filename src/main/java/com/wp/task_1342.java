package com.wp;

/**
 * @author: wp
 * @Title: task_1342  1342. 将数字变成 0 的操作次数
 * @Description: https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 * @date 2022/1/31 12:34
 */
public class task_1342 {

    public int numberOfSteps(int num) {
        if (0==num) {
            return 0;
        }
        int n = 0;
        while(num!=0) {
            if (num%2==0) {
                num = num / 2;
            }else{
                num--;
            }
            n++;
        }
        return n;
    }
}
