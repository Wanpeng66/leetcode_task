package com.wp;


/**
 * @author: wp
 * @Title: task_134 134. 加油站
 * @Description: https://leetcode-cn.com/problems/gas-station/
 * @date 2022/2/20 15:09
 */
public class task_134 {

    public static void main( String[] args ) {
        int[] gas = {5,1,2,3,4};
        int[] cost = {4,4,1,5,1};
        System.out.println( canCompleteCircuit( gas, cost ) );
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int spare = 0;
        int minSpare = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < len; i++) {
            spare += gas[i] - cost[i];
            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }

        return spare < 0 ? -1 : (minIndex + 1) % len;
    }
}
