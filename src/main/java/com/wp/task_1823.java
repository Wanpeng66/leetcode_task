package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_1823  1823. 找出游戏的获胜者
 * @Description: https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/
 * @date 2022/5/4 13:13
 */
public class task_1823 {
    public static void main( String[] args ) {
        System.out.println( findTheWinner( 6, 5 ) );
    }
    public static int findTheWinner(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add( i, i+1 );
        }
        int index = 0;
        for (int i = 0; i < n - 1; i++) {
            int j = (index + k) % list.size();
            if (j>0) {
                j--;
            }else{
                j = list.size()-1;
            }
            list.remove( j );
            index = j;
        }
        return list.get( 0 );
    }
}
