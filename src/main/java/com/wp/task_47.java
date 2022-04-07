package com.wp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: wp
 * @Title: task_47  47. 全排列 II
 * @Description: https://leetcode-cn.com/problems/permutations-ii/
 * @date 2022/4/7 10:02
 */
public class task_47 {
    public static void main( String[] args ) {
        int[] nums = {1, 1, 2};
        List<List<Integer>> lists = permuteUnique( nums );
        System.out.println( lists );
    }

    public static List<List<Integer>> permuteUnique( int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        int len = nums.length;
        boolean[] vistied = new boolean[len];
        dfs(nums, len, 0, new ArrayList<>(), res, vistied);
        return new ArrayList<>(res);
    }

    private static void dfs( int[] nums, int len, int index, ArrayList<Integer> list, Set<List<Integer>> res, boolean[] vistied ) {
        if (index>=len) {
            res.add( new ArrayList<>(list) );
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!vistied[i]) {
                int num = nums[i];
                list.add( num );
                vistied[i] = true;
                dfs( nums, len, index+1, list, res, vistied );
                list.remove( list.size()-1 );
                vistied[i] = false;
            }
        }
    }
}
