package com.wp;

import java.util.Arrays;

/**
 * @author: wp
 * @Title: task_1051  1051. 高度检查器
 * @Description: https://leetcode.cn/problems/height-checker/
 * @date 2022/6/13 11:07
 */
public class task_1051 {
    public int heightChecker(int[] heights) {
        int[] copy = Arrays.copyOf( heights, heights.length );
        Arrays.sort( copy );
        int num = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i]!=copy[i]) {
                num++;
            }
        }
        return num;
    }
}
