package com.wp;

/**
 * @author: wp
 * @Title: task_349  349. 两个数组的交集
 * @Description:  https://leetcode-cn.com/problems/intersection-of-two-arrays/
 * @date 2022/2/5 15:29
 */
public class task_349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        int[] tmp = new int[1001];
        int[] little = nums1.length>nums2.length?nums2:nums1;
        int[] large = nums1.length>nums2.length?nums1:nums2;
        int num = 0;
        for (int i : little) {
            boolean flag = false;
            if (0!=tmp[i]) {
                continue ;
            }
            for (int j : large) {
                if (j == i) {
                    flag = true;
                    break;
                }
            }
            if (flag && 0==tmp[i]) {
                tmp[i] += 1;
                num++;
            }
        }
        int[] res = new int[num];
        for (int i = 0; i < tmp.length; i++) {
            if (0!=tmp[i]) {
                res[--num] = i;
            }
        }
        return res;
    }
}
