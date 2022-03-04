package com.wp;

/**
 * @author: wp
 * @Title: task_45  45. 跳跃游戏 II
 * @Description: https://leetcode-cn.com/problems/jump-game-ii/
 * @date 2022/3/3 14:23
 */
public class task_45 {

    public static void main( String[] args ) {
        int[] nums = {1,2,1,1,1};
        System.out.println( jump( nums ) );
    }

    public static int jump(int[] nums) {
        if (nums.length==1) {
            return 0;
        }
        int step = 0;
        for (int i = 0; i < nums.length;) {
            int num = nums[i];
            if (i+num+1>=nums.length) {
                step++;
                break;
            }
            int max = 0;
            int j = i+1;
            for (int k = 0; k < num; k++) {
                int offset = i+1+k;
                if (offset+nums[offset]>=max) {
                    max = offset+nums[offset];
                    j = offset;
                }
            }
            i = j;
            step++;
        }
        return step;
    }
}
