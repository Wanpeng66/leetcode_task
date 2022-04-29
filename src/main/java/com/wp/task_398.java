package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_398
 * @Description:
 * @date 2022/4/25 11:18
 */
public class task_398 {
    public static void main( String[] args ) {
        int[] nums = {1,2,3,3,3};
        task_398 task398 = new task_398( nums );
        System.out.println( task398.pick( 3 ) );
        System.out.println( task398.pick( 1 ) );
    }

    static Random random = new Random();
    static Map<Integer, List<Integer>> map;
    public task_398(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> list = map.getOrDefault( num, new ArrayList<>() );
            list.add( i );
            map.put( num, list );
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get( target );
        int size = list.size();
        int i = random.nextInt( size );
        return list.get( i );
    }
}
