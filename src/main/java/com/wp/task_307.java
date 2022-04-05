package com.wp;


/**
 * @author: wp
 * @Title: task_307 307. 区域和检索 - 数组可修改
 * @Description: https://leetcode-cn.com/problems/range-sum-query-mutable/
 * @date 2022/4/4 10:11
 */
public class task_307 {
    public static void main( String[] args ) {
        NumArray array = new NumArray( new int[]{-1} );
        System.out.println( array.sumRange( 0, 0 ) );
        array.update( 0,1 );
        System.out.println( array.sumRange( 0, 0 ) );
    }
}
class NumArray {
    int[] sums = null;
    int[][] blocks = null;
    int size = 0;
    public NumArray(int[] nums) {
        int length = nums.length;
        size = (int) Math.sqrt( length );
        int i = length % size == 0 ? length / size : ( length / size ) + 1;
        sums = new int[i];
        blocks = new int[i][size];
        int currentBlock = 0;
        int sum = 0;
        for (int j = 0; j < nums.length; j++) {
            int index = j / size;
            if (index != currentBlock) {
                currentBlock = index;
            }
            sums[currentBlock] += nums[j];
            blocks[index][j%size] = nums[j];
        }
    }

    public void update(int index, int val) {
        int i = index / size;
        int old = blocks[i][index % size];
        blocks[i][index % size] = val;
        sums[i] += (val - old);
    }

    public int sumRange(int left, int right) {
        int leftBlock = left / size;
        int rightBlock = right / size;
        int sum = 0;
        //如果left和right落在同一区间 则只计算哪一个区间从left%size到right%size的累加和即可
        if (leftBlock == rightBlock) {
            int[] block = blocks[leftBlock];
            for (int i = left % size; i <= right % size; i++) {
                sum += block[i];
            }
        }else{
            //如果落在不同区间 则分区域计算累加和
            for (int i = leftBlock+1; i < rightBlock; i++) {
                sum += sums[i];
            }
            int[] block = blocks[leftBlock];
            for (int i = left % size; i < size; i++) {
                sum += block[i];
            }
            block = blocks[rightBlock];
            for (int i = 0; i <= right % size; i++) {
                sum += block[i];
            }
        }
        return sum;
    }
}