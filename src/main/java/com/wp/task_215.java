package com.wp;

/**
 * @author: wp
 * @Title: task_215 215. 数组中的第K个最大元素
 * @Description: https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @date 2022/2/3 17:27
 */
public class task_215 {

    public static void main( String[] args ) {
        task_215 task = new task_215();
        System.out.println( task.findKthLargest( new int[]{3, 2, 1, 5, 6, 4, 2}, 2 ) );
    }

    public int findKthLargest(int[] nums, int k) {
        return quick_sort(nums,0,nums.length-1,nums.length-k);
    }

    private int quick_sort( int[] nums, int p, int r, int k ) {
        int q = partion(nums, p, r);
        if (q==k) {
            return nums[q];
        } else if (q>k) {
            return quick_sort( nums,p, q-1, k );
        }else{
            return quick_sort( nums,q+1, r, k );
        }

    }

    private int partion( int[] nums, int p, int r ) {
        int j = p,k = p;
        for(;k< nums.length - 1;k++){
            if (nums[k]<nums[r]) {
                swap( nums,j,k );
                j++;
            }
        }
        swap( nums,j,r );
        return j;
    }
    private void swap( int[] array, int p, int index ) {
        if(p==index) return;
        int tmp = array[p];
        array[p] = array[index];
        array[index] = tmp;
    }

}
