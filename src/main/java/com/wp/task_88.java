package com.wp;

/**
 * @author: wp
 * @Title: task_88 88. 合并两个有序数组
 * @Description: https://leetcode-cn.com/problems/merge-sorted-array/
 * @date 2022/2/3 13:19
 */
public class task_88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m+n];
        int i = 0,k = 0,offset = 0;
        while (i<m&&k<n) {
            if (nums1[i]<=nums2[k]) {
                tmp[offset++] = nums1[i++];
            }else{
                tmp[offset++] = nums2[k++];
            }
        }
        while (i<m) {
            tmp[offset++] = nums1[i++];
        }
        while (k<n) {
            tmp[offset++] = nums2[k++];
        }
        for (int j = 0; j < tmp.length; j++) {
            nums1[j] = tmp[j];
        }
    }
}
