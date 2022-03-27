package com.wp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: task_5268  5268. 找出两数组的不同
 * @Description: https://leetcode-cn.com/contest/weekly-contest-286/problems/find-the-difference-of-two-arrays/
 * @date 2022/3/27 10:33
 */
public class task_5268 {
    public static void main( String[] args ) {
        int[] nums1 = {1,2,3,3};
        int[] nums2 = {1,1,2,2};
        List<List<Integer>> difference = findDifference( nums1, nums2 );
        System.out.println( difference );
    }
    public static List<List<Integer>> findDifference( int[] nums1, int[] nums2) {
        List<List<Integer>> answer = new ArrayList<>();
        Set<Integer> list1 = Arrays.stream( nums1 ).boxed().collect( Collectors.toSet() );
        Set<Integer> list2 = Arrays.stream( nums2 ).boxed().collect( Collectors.toSet() );
        ArrayList<Integer> list3 = new ArrayList<>( list1 );
        list1.removeAll( list2 );
        answer.add( 0,new ArrayList<>(list1) );
        list2.removeAll( list3 );
        answer.add( 1, new ArrayList<>(list2));
        return answer;
    }
}
