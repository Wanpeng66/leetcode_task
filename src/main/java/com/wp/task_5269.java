package com.wp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: wp
 * @Title: task_5269 5269. 从栈中取出 K 个硬币的最大面值和
 * @Description: https://leetcode-cn.com/contest/weekly-contest-286/problems/maximum-value-of-k-coins-from-piles/
 * @date 2022/3/27 11:21
 */
public class task_5269 {
    public static void main( String[] args ) {
        List<List<Integer>> piles = new ArrayList<>();
        for(int i=0;i<7;i++){
            if (i<6) {
                List<Integer> list = Arrays.asList(100);
                piles.add( list );
            }else{
                List<Integer> list = Arrays.asList(1,1,1,1,1,1,700);
                piles.add( list );
            }
        }
        int sum = maxValueOfCoins( piles, 7 );
        System.out.println( sum );
    }
    public static int maxValueOfCoins( List<List<Integer>> piles, int k) {
        int[] offsets = new int[piles.size()];
        long sum = 0;
        for (int i = 0; i < k; i++) {
            int max = Integer.MIN_VALUE;
            int index = 0;
            for (int j = 0; j < offsets.length; j++) {
                List<Integer> list = piles.get( j );
                if (offsets[j]<list.size()) {
                    Integer val = list.get( offsets[j] );
                    if (val>max) {
                        max = val;
                        index = j;
                    }
                }
            }
            sum += max;
            offsets[index]++;
        }
        return (int) sum;
    }
}
