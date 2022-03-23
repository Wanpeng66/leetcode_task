package com.wp;

import cn.hutool.core.io.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_440  440. 字典序的第K小数字
 * @Description: https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 * @date 2022/3/23 10:33
 */
public class task_440 {

    static List<Integer> list = new ArrayList<>();
    public static void main( String[] args ) throws IOException {
        long start = System.currentTimeMillis();
        int kthNumber = findKthNumber( 100, 90 );
        long l = System.currentTimeMillis() - start;
        System.out.println(kthNumber);
        System.out.println(l);
        /*System.out.println("-----------------");
        start = System.currentTimeMillis();
         method1( 681692778, 351251360 );

        System.out.println("done..........");*/
    }
    public static int findKthNumber(int n, int k) {
        int[] offset = {0};
        int[] res = {0};
        int count = k;
        for(int i=1;i<10;i++){
            int steps = getCount( i, n );
            if (steps<count) {
                offset[0] += steps;
                count -= steps;
                continue;
            }
            offset[0]++;
            dfs( i,offset,res,n,k);
            if (res[0]!=0) {
                break;
            }
        }
        return res[0];
    }

    private static void dfs( long num, int[] offset, int[] res, int n, int k ) {
        if (res[0]!=0) {
            return;
        }
        if (offset[0]==k) {
            res[0] = (int) num;
            return;
        }
        for(int i=0;i<10;i++){
            if (res[0]!=0) {
                break;
            }
            long sum = num * 10 + i;
            if (sum<=n) {
                int steps = getCount( (int) sum, n );
                if (offset[0]+steps<k) {
                    offset[0]+=steps;
                    continue;
                }else{
                    offset[0]++;
                    if (offset[0]==k) {
                        res[0] = (int) sum;
                        break;
                    }
                    dfs( sum, offset, res, n,k );
                }
            }else{
                break;
            }
        }
    }
    public static int getCount(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }

    private static Integer method1( int n, int k ) {
        list.clear();
        for (int i = 1; i <= n; i++) {
            list.add( i );
        }
        list.sort( ( o1, o2 ) -> String.valueOf( o1 ).compareTo( String.valueOf( o2 ) ) );
        return list.get( k -1 );
    }

}

