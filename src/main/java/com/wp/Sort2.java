package com.wp;


import java.util.Arrays;

/**
 * @author: wp
 * @Title: Sort2
 * @Description:
 * @date 2022/1/31 13:26
 */
public class Sort2 {
    public static void main(String[] args){
        int[] array = {1,2,0,10,44,99,22,2};
        //int[] ints = Arrays.copyOfRange( new int[2], 1, 2 );
        merge_sort( array );
        System.out.println( array );
    }

    public static void merge_sort(int[] array ){
        int size = array.length;
        split(array,0, size-1);
    }
    //归并排序 先递归拆分成最小单元 然后再两两合并（相当于合并两个有序数组）
    static void  split(int[] array, int p, int r){
        if (p>=r) {
            return;
        }
        int q = (p+r)/2;
        split( array, p, q );
        split( array, q+1, r );
        merge( array, Arrays.copyOfRange( array, p, q+1 ), Arrays.copyOfRange( array, q+1, r+1 ), p );
    }
    private static void merge( int[] array, int[] one, int[] two, int p ) {
        int offset1 = 0, offset2 = 0;
        while (offset1<one.length && offset2<two.length) {
            if (one[offset1]<=two[offset2]) {
                array[p++] = one[offset1++];
            }else{
                array[p++] = two[offset2++];
            }
        }
        if (offset1<one.length) {
            for (; offset1 < one.length; offset1++) {
                array[p++] = one[offset1];
            }
        }
        if (offset2< two.length) {
            for (; offset2 < two.length; offset2++) {
                array[p++] = two[offset2];
            }
        }
    }
}
