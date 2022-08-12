package com.wp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wp
 * @Title: task_121  121. 买卖股票的最佳时机
 * @Description: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * @date 2022/2/22 15:41
 */
public class task_121 {
    public static void main( String[] args ) {
        int[] prices = {4,7,1,2,2,2,2};

        System.out.println( maxProfit( prices ) );
    }

    public static int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    private static int method1( int[] prices ) {
        int[] profit = {0};
        Set<Integer> visited = new HashSet<>();
        dfs( prices,0, false, false, 0, profit, visited );
        return profit[0];
    }

    private static void dfs( int[] prices, int i, boolean buy, boolean sell, int j, int[] profit, Set<Integer> visited ) {
        if (buy && sell ) {
            if (j>profit[0]) {
                profit[0] = j;
            }
            return;
        }
        if (i>=prices.length) {
            return;
        }
        int price = prices[i];
        if (!buy) {
            dfs( prices, i+1, true, false, j-price, profit, visited );
            dfs( prices, i+1, false, false, j, profit, visited );
        }else if(!sell){
            if (!visited.contains( j+price )) {
                visited.add( j+price );
                dfs( prices, i+1, true, true, j+price, profit, visited );
            }
            dfs( prices, i+1, true, false, j, profit, visited );
        }
    }

}
