package com.wp;

/**
 * @author: wp
 * @Title: Sort3
 * @Description:
 * @date 2022/2/1 12:13
 */
public class Sort3 {

    public static void main( String[] args ) {
        int[] array = {1,2,0,10,44,99,22,2};
        quick_sort( array );
        System.out.println( array );
    }
    //快排就是每次找到一个元素放到合适位置，致使前面的元素小于等于它，后面的元素大于它
    public static void quick_sort(int[] array){
        int size = array.length;
        sort(array, 0, size-1);
    }
    public static void sort(int[] array, int p, int r){
        if (p>=r) {
            return;
        }
        int q = partion(array, p, r);
        sort( array, p, q-1 );
        sort( array, q+1, r );
    }
    private static int partion( int[] array, int p, int r ) {
        int index = p;
        for (int i = p; i < r; i++) {
            if (array[i]<=array[r]) {
                swap( array, i, index );
                index++;
            }
        }
        swap( array, r, index );
        return index;
    }
    private static void swap( int[] array, int p, int index ) {
        int tmp = array[p];
        array[p] = array[index];
        array[index] = tmp;
    }
}
