package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_40  40. 组合总和 II
 * @Description: https://leetcode-cn.com/problems/combination-sum-ii/
 * @date 2022/3/26 9:15
 */
public class task_40 {

    public static void main( String[] args ) {
        //int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] nums = {10,1,2,7,6,1,5};
        List<List<Integer>> lists = combinationSum2( nums, 8 );
        System.out.println( lists );

    }
    public static List<List<Integer>> combinationSum2( int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        Map<String,Boolean> cache = new HashMap<>();
        sum(candidates,target,0,0,new ArrayList<>(),result,cache);
        return new ArrayList<>(result);
    }

    private static void sum( int[] candidates, int target, int i, int sum, ArrayList<Integer> integers, List<List<Integer>> result,Map<String,Boolean> cache) {
        if (sum==target && !cache.containsKey( integers.toString() )) {
            cache.put( integers.toString(),true );
            result.add( new ArrayList<>( integers ) );
            return;
        }
        if (i>=candidates.length) {
            return;
        }
        //每一个元素都有两种决策
        //不选择下标为i的元素
        sum( candidates,target,i+1,sum,integers,result,cache );
        //选择下标为i的元素
        int num = candidates[i];
        if (sum+ num <=target) {
            integers.add( num );
            sum( candidates,target,i+1,sum+num,integers,result,cache );
            integers.remove( integers.size()-1 );
        }

    }
}
