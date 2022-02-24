package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_322  322. 零钱兑换
 * @Description: https://leetcode-cn.com/problems/coin-change/
 * @date 2022/2/23 10:38
 */
public class task_322 {

    public static void main( String[] args ) {
        System.out.println( coinChange( new int[]{2}, 3 ) );
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        int[] mem = new int[amount+1];
        for (int coin : coins) {
            if (coin<=amount) {
                mem[coin] = 1;
            }
        }
        return f(coins,amount,mem );
    }

    private static int f(int[] coins, int amount, int[] mem) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (mem[amount] != 0) {
            return mem[amount];
        }
        int min = amount+1;
        for (int coin : coins) {
            int res = f(coins, amount - coin, mem);
            if (res >= 0 && res < min) {
                min = res;
            }
        }
        if(min==amount+1){
            min = -1;
        }else{
            min++;
        }
        mem[amount] = min;
        return min;
    }

    private static int method1( int[] coins, int amount ) {
        int[] min = {Integer.MAX_VALUE};
        List<String> hasCheck = new ArrayList<>();
        coinChange( coins, 0L,0, amount,min,hasCheck );
        if (min[0]==Integer.MAX_VALUE) {
            return -1;
        }
        return min[0];
    }

    private static void coinChange( int[] coins, long total, int num, int amount, int[] min, List<String> hasCheck ) {
        String key = total+"-"+num;
        if (hasCheck.contains( key )) {
            return;
        }
        if (total>amount) {
            hasCheck.add( key );
            return;
        }
        if (total==amount && min[0]>num) {
            min[0] = num;
            hasCheck.add( key );
            return;
        }
        for (int coin : coins) {
            total += coin;
            num++;
            coinChange( coins,total,num,amount,min,hasCheck );
            hasCheck.add( total+"-"+num );
            total -= coin;
            num--;
        }
    }
}
