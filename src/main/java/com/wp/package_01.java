package com.wp;

/**
 * @author: wp
 * @Title: package_01
 * @Description: 01背包问题 对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，在满足背包最大重量限
 * 制的前提下，背包中物品总重量的最大值是多少呢？
 * @date 2022/2/23 15:16
 */
public class package_01 {

    public static void main( String[] args ) {
        System.out.println( solution( new int[]{2,4,6,}, 11 ) );
    }

    public static int solution(int[] array, int num){
        int[] max = {Integer.MIN_VALUE};
        f(array, 0, 0, num, max);
        return max[0];
    }

    private static void f( int[] array, int offset, int total, int num, int[] max ) {
        if (total>num) {
            return;
        }
        if (total==num || offset>=array.length) {
            if (total>max[0]) {
                max[0] = total;
            }
            return;
        }
        f(array, offset+1, total, num, max);
        f( array, offset+1, total+array[offset], num, max );
    }

}
