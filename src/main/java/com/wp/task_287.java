package com.wp;

/**
 * @author: wp
 * @Title: task_287  287. 寻找重复数
 * @Description: https://leetcode-cn.com/problems/find-the-duplicate-number/
 * @date 2022/2/7 12:58
 */
public class task_287 {

    public static void main( String[] args ) {
        int[] nums = {1,3,4,2,3};
        System.out.println( findDuplicate( nums ) );
    }

    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }
}
