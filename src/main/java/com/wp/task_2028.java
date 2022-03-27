package com.wp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wp
 * @Title: task_2028 2028. 找出缺失的观测数据
 * @Description:  https://leetcode-cn.com/problems/find-missing-observations/
 * @date 2022/3/27 15:10
 */
public class task_2028 {

    public static void main( String[] args ) {
        int[] rolls = {4,5,6,2,3,6,5,4,6,4,5,1,6,3,1,4,5,5,3,2,3,5,3,2,1,5,4,3,5,1,5};
        int[] ints = missingRolls2( rolls, 4, 40 );
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] missingRolls2(int[] rolls, int mean, int n) {
        int initVal = (rolls.length+n)*mean - Arrays.stream( rolls ).sum();
        if (initVal/n>6 || initVal<n) {
            return new int[0];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int val = Math.min( initVal-(n-1-i),6 );
            res[i] = val;
            initVal -= val;
        }
        return res;
    }
    public static int[] missingRolls(int[] rolls, int mean, int n) {
        List<Integer> nums = Arrays.asList( 6,5,4,3,2,1 );
        int initVal = (rolls.length+n)*mean - Arrays.stream( rolls ).sum();
        if (initVal/n>6 || initVal<n) {
            return new int[0];
        }
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] isDone = {false};
        calculate(nums,n,0,res,isDone,initVal);
        return res.stream().mapToInt( Integer::intValue ).toArray();
    }

    private static void calculate( List<Integer> nums, int n,int sum, ArrayList<Integer> res, boolean[] isDone,int total ) {
        if (isDone[0] || res.size()>n) {
            return;
        }
        if (res.size()==n) {
            if(sum == total){
                isDone[0] = true;
            }
            return;
        }
        for (Integer num : nums) {
            if (sum+num>total) {
                continue;
            }
            sum += num;
            res.add( num );
            int size = res.size()-1;
            calculate( nums,n,sum,res,isDone,total );
            if (isDone[0]) {
                return;
            }
            sum -= num;
            res.remove( size );
        }
    }


}
