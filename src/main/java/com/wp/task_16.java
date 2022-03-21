package com.wp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: task_16  16. 最接近的三数之和
 * @Description: https://leetcode-cn.com/problems/3sum-closest/
 * @date 2022/3/21 16:07
 */
public class task_16 {

    public static void main( String[] args ) {
        System.out.println( threeSumClosest( new int[]{0,2,1}, 3 ) );
    }
    public static int threeSumClosest(int[] nums, int target) {
        List<Integer> list = Arrays.stream( nums ).boxed().collect( Collectors.toList() );
        list.sort( Comparator.reverseOrder() );
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < list.size()-2; i++) {
            for (int j = i+1; j < list.size()-1; j++) {
                for (int k = j+1; k < list.size(); k++) {
                    int sum = list.get( i ) + list.get( j ) + list.get( k );
                    int val = Math.abs( sum - target );
                    if (val<min) {
                        min = val;
                        res = sum;
                    }
                }
            }
        }
        return res;
    }
}
