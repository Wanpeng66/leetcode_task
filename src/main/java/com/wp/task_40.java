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
        Set<List<Integer>> result = new HashSet<>();
        Map<Integer,HashSet<Integer>> cache = new HashMap<>();
        //dfs(candidates, target, 0, 0, new ArrayList<>(), result);
        sum(candidates,target,0,0,new ArrayList<>(),result,cache);
        return new ArrayList<>(result);
    }

    private static void dfs( int[] candidates, int target, int i, int sum, ArrayList<Integer> list, Set<List<Integer>> result) {
        if (sum==target) {
            result.add( new ArrayList<>(list) );
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            if ( sum + candidates[j] > target) {
                continue;
            }
            if ( j>0 && candidates[j]!=candidates[j-1]) {
                list.add( candidates[j] );
                dfs( candidates, target, j+1, sum+candidates[j], list, result );
                list.remove( list.size()-1 );
            }
        }
    }

    private static void sum( int[] candidates, int target, int i, int sum, ArrayList<Integer> integers, Set<List<Integer>> result,Map<Integer,HashSet<Integer>> cache) {
        if (sum==target) {
            for (int j = 0; j < integers.size(); j++) {
                HashSet<Integer> set = cache.getOrDefault( j, new HashSet<>() );
                set.add( integers.get( j ) );
                cache.put( j, set );
            }
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
        HashSet<Integer> set = cache.get( integers.size() - 1 );
        if (sum+ num <=target && (null==set || !set.contains( num ))) {
            integers.add( num );
            sum( candidates,target,i+1,sum+num,integers,result,cache );
            integers.remove( integers.size()-1 );
        }

    }
}
