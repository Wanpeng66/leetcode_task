package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_11 11. 盛最多水的容器
 * @Description:  https://leetcode-cn.com/problems/container-with-most-water/
 * @date 2022/2/20 14:18
 */
public class task_11 {

    public static void main( String[] args ) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println( maxArea( height ) );
    }

    public static int maxArea(int[] height) {
        Map<String,Long> hasArea = new HashMap<>();
        Long maxArea = 0l;
        int left = 0,right = height.length-1;
        while (left!=right) {
            long high = height[left]<height[right]?height[left]:height[right];
            long area = ((long)high) * ((long)(right-left));
            if (area>maxArea) {
                maxArea = area;
            }
            if (height[left]<height[right]) {
                left++;
            }else{
                right--;
            }
        }
        return maxArea.intValue();
    }

}
