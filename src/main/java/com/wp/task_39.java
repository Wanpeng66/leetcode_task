package com.wp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author: wp
 * @Title: task_39 39. 组合总和
 * @Description: https://leetcode-cn.com/problems/combination-sum/
 * @date 2022/3/9 15:43
 */
public class task_39 {

    public static void main( String[] args ) {
        int[] candidates = {2,3,5};
        List<List<Integer>> lists = combinationSum( candidates, 8 );
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer+",");
            }
            System.out.println();
        }
    }
    public static List<List<Integer>> combinationSum( int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        sum(candidates,target,0,list,lists);
        for (List<Integer> res : lists) {
            Collections.sort( res );
        }
        HashSet<List<Integer>> set = new HashSet<>( lists );
        return new ArrayList<>(set);
    }

    private static void sum( int[] candidates, int target, long total, List<Integer> list, List<List<Integer>> lists ) {
        if (total==target) {
            lists.add( new ArrayList<>(list) );
            return;
        }
        for (int candidate : candidates) {
            if (total+candidate<=target) {
                list.add( candidate );
                total+=candidate;
                sum( candidates,target,total,list, lists );
                list.remove( list.size()-1 );
                total-=candidate;
            }
        }
    }
}
