package com.wp;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author: wp
 * @Title: Sort
 * @Description:  常见的排序算法
 * @date 2022/2/6 12:27
 */
public class Sort {

    public static void main( String[] args ) {
        int[] nums = {10,2,3,9,11,0};
        //bubble_sort( nums );
        //insert_sort( nums );
        //selection_sort( nums );
        //merge_sort( nums );
        //quick_sort( nums );
        //mppx( nums );
        //xzpx( nums );
        //crpx( nums );
        gbpx( nums );
        System.out.println( nums );
    }

    public static void mppx(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j]>nums[j+1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                }
            }
        }
    }

    public static void xzpx(int[] nums){
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int max = Integer.MIN_VALUE;
            int offset = i;
            for (int j = i; j < size; j++) {
                if (nums[j]>max) {
                    max = nums[j];
                    offset = j;
                }
            }
            nums[offset] = nums[i];
            nums[i] = max;
        }
    }


    public static void crpx(int[] nums){
        int size = nums.length;
        for (int i = 1; i < size; i++) {
            int offset = i;
            int num = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (num<nums[j]) {
                    nums[j+1] =nums[j];
                    offset = j;
                }else{
                    break;
                }
            }
            nums[offset] = num;
        }
    }


    public static void gbpx(int[] nums){
        int size = nums.length;
        gbpx_split(nums, 0, size-1);
    }

    private static void gbpx_split( int[] nums, int left, int right ) {
        if (left>=right) {
            return;
        }
        int middle = (right - left) / 2 + left;
        gbpx_split( nums, left, middle );
        gbpx_split( nums, middle+1, right );
        gbpx_merge(nums ,Arrays.copyOfRange( nums, left, middle+1 ), Arrays.copyOfRange( nums,middle+1, right+1 ), left);

    }

    private static void gbpx_merge( int[] nums, int[] leftArray, int[] rightArray, int start ) {
        int leftOffset = 0,rightOffset = 0;
        while (leftOffset<leftArray.length && rightOffset<rightArray.length) {
            if (leftArray[leftOffset]<=rightArray[rightOffset]) {
                nums[start++] = leftArray[leftOffset++];
            }else{
                nums[start++] = rightArray[rightOffset++];
            }
        }
        while (leftOffset<leftArray.length) {
            nums[start++] = leftArray[leftOffset++];
        }
        while (rightOffset<rightArray.length) {
            nums[start++] = rightArray[rightOffset++];
        }
    }


    //冒泡排序 升序排列
    //原地排序 稳定算法 时间复杂度O(n*n)
    public static void bubble_sort(int[] nums){
        int size = nums.length;
        //外层定义循环多少次
        for (int i = 0; i < size; i++) {
            //定义此次循环是否有移动元素
            boolean flag = false;
            //内层循环依次判断相邻两个元素的大小 大的往后挪
            for (int j = 0; j < size - i - 1; j++) {
                if (nums[j]>nums[j+1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                //如果没有挪动元素
                //则说明此时数组已经有序 跳出循环即可
                break;
            }
        }
    }


    //插入排序 升序排列
    //原地排序 稳定算法  时间复杂度O(n*n)
    //将数组分为有序区域和无序区域
    // 有序区域默认是第一个元素
    // 然后将无序区域内的元素依次往有序区域插入 找到合适的位置
    public static void insert_sort(int[] nums){
        int size = nums.length;
        //循环无序区域 默认从下标1开始
        for (int i = 1; i < size; i++) {
            int val = nums[i];
            int offset = i;
            //将无序区域元素与有序区域元素比较 找到合适的插入位置
            for (int j = i-1; j >= 0; j--) {
                if (nums[j]>val) {
                    nums[j+1] = nums[j];
                    offset = j;
                }else{
                    break;
                }
            }
            nums[offset] = val;
        }

    }


    //选择排序 升序排列
    //原地排序 不稳定算法 时间复杂度O(n*n)
    //将数组分为有序区域和无序区域
    // 有序区域默认无
    // 然后依次从无序区域选择最小的元素往有序区域放
    public static void selection_sort(int[] nums){
        int size = nums.length;
        //依次循环无序区域
        for (int i = 0; i < size; i++) {
            //定义此次循环最小值的下标
            int min = i;
            //查找最小值
            for (int j = i+1; j < size; j++) {
                if (nums[j]<nums[min]) {
                    min = j;
                }
            }
            //将最小值放到有序区域
            int tmp = nums[min];
            nums[min] = nums[i];
            nums[i] = tmp;
        }
    }


    //归并排序 升序排列
    //非原地排序 稳定算法 时间复杂度O(n*logn)
    //先将数组拆分成最小区间
    // 然后依次两两合并 最后就是有序的
    public static void merge_sort(int[] nums){
        int size = nums.length;
        split(nums, 0, size-1);
    }

    public static void split(int[] nums, int p, int r){
        //如果p>=r 则说明区间无可再分
        if (p>=r) {
            return;
        }
        //计算划分区间的中间值
        int q = (p+r)/2;
        //将下标p到r的区间再划分为两部分
        split( nums, p, q );
        split( nums, q+1, r );
        //合并两个有序区间
        merge(nums, Arrays.copyOfRange( nums,p,q+1 ),
                Arrays.copyOfRange( nums,q+1, r+1 ), p );
    }

    private static void merge( int[] nums, int[] one, int[] two, int start ) {
        int o1 = 0,o2 = 0;
        while(o1<one.length && o2<two.length){
            if (one[o1]<=two[o2]) {
                nums[start++] = one[o1++];
            }else{
                nums[start++] = two[o2++];
            }
        }
        while (o1<one.length) {
            nums[start++] = one[o1++];
        }
        while (o2< two.length) {
            nums[start++] = two[o2++];
        }
    }


    //快速排序 升序排列
    //原地排序 不稳定算法 时间复杂度O(n*logn)
    //先找到一个元素将其放到合适位置
    // 保证左边的都小于它 右边的都大于等于它
    // 然后再递归左右两边的区域 都执行这样的逻辑
    // 则最后一定是有序的
    public static void quick_sort(int[] nums){
        int size = nums.length;
        quick(nums, 0, size-1);
    }

    private static void quick( int[] nums, int p, int r ) {
        //如果p>=r 则说明区域中只有一个元素 肯定是有序的
        if (p>=r) {
            return;
        }
        //获得中间元素的下标
        int q = partition(nums, p, r);
        //递归左右两边的区域执行分区函数
        quick( nums,p, q-1 );
        quick( nums, q+1, r );
    }

    //分区函数 将选择的元素插入到合适的位置
    // 保证左边的元素都小于它 右边的都大于等于它
    private static int partition( int[] nums, int p, int r ) {
        getValue(nums, p, r);
        int val = nums[r];
        int offset = p;
        for (; p <= r; p++) {
            if (nums[p]<val) {
                int num = nums[p];
                nums[p] = nums[offset];
                nums[offset] = num;
                offset++;
            }
        }
        int num = nums[r];
        nums[r] = nums[offset];
        nums[offset] = num;
        return offset;
    }

    //三数取中法将合适挪到区域末尾
    private static void getValue( int[] nums, int p, int r ) {
        int offset = r;
        int first = nums[p];
        int last = nums[r];
        int middle = nums[(p+r)/2];
        if ((first>=last && first<=middle) ||
                (first>=middle && first<=last)) {
            offset = p;
        } else if ((middle>=first && middle<=last) ||
                (middle>=last || middle<=first)) {
            offset = (p+r)/2;
        }else{
            offset = r;
        }
        int tmp = nums[offset];
        nums[offset] = nums[r];
        nums[r] = tmp;
    }

}