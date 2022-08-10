package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_710   710. 黑名单中的随机数
 * @Description: https://leetcode.cn/problems/random-pick-with-blacklist/
 * @date 2022/6/26 12:55
 */
public class task_710 {
    public static void main( String[] args ) {
        Solution solution = new Solution( 7,new int[]{2,3,5} );
        System.out.println( solution.pick() );
        System.out.println( solution.pick() );
    }

    static class Solution {
        Map<Integer,Integer> map;
        Random random = new Random();
        int max;


        public Solution(int n, int[] blacklist) {
            map = new HashMap<>();
            max = n-blacklist.length;
            Arrays.sort( blacklist );
            Set<Integer> visited = new HashSet<>();
            for (int i = blacklist.length - 1; i >= 0; i--) {
                int value = blacklist[i];
                if (value<max) {
                    int tmp = max;
                    while (visited.contains( tmp )) {
                        tmp++;
                    }
                    map.put( value,tmp );
                    visited.add( tmp );
                }else{
                    visited.add( value );
                }

            }
        }

        public int pick() {
            int x = random.nextInt(max-1);
            return map.getOrDefault(x, x);
        }
    }
}
