package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_46 46. 全排列
 * @Description: https://leetcode-cn.com/problems/permutations/
 * @date 2022/4/2 19:04
 */
public class task_46 {
    public static void main( String[] args ) {
        int[] nums = {1,2,3};
        List<List<Integer>> lists = permute( nums );
        System.out.println( lists );
    }

    public static List<List<Integer>> permute( int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        boolean[] isVisited = new boolean[length];
        dfs(nums,new ArrayList<>(),res,length,isVisited);
        return res;

    }

    private static void dfs( int[] numList, ArrayList<Integer> list, List<List<Integer>> res, int length, boolean[] isVisited ) {
        if (list.size()==length) {
            res.add( new ArrayList<>(list) );
            return;
        }
        for (Integer i = 0; i < numList.length; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                list.add( numList[i] );
                dfs( numList, list, res, length, isVisited );
                list.remove( (Integer) numList[i] );
                isVisited[i] = false;
            }
        }
    }
}
