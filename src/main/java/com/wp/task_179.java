package com.wp;

import java.util.Arrays;

/**
 * @author: wp
 * @Title: task_179  179. 最大数
 * @Description: https://leetcode-cn.com/problems/largest-number/
 * @date 2022/2/4 13:51
 */
public class task_179 {

    public static void main( String[] args ) {
        int[] nums = {3,30,34,5,9};
        System.out.println( largestNumber( nums ) );
    }

    public static String largestNumber(int[] nums) {
        int n = nums.length;
        String[] array = new String[n];
        //将数字转为字符串
        for(int i=0; i<n; i++){
            array[i] = String.valueOf(nums[i]);
        }
        //将字符串从小到大排列
        //如果a+b > b+a 那就说明 a应该放在b前面  但此时是从小到大排列 所以b就应该放在a 前面
        Arrays.sort(array,(a,b)-> (a+b).compareTo(b+a) );
        StringBuilder sb = new StringBuilder();
        //从后往前遍历 挨个取出拼接
        for (int i = array.length - 1; i >= 0; i--) {
            sb.append(array[i]);
        }
        String str = sb.toString();
        //如果第一个字符是0 则直接返回0
        if (str.startsWith( "0" )) {
            return "0";
        }
        return str;
    }

}
