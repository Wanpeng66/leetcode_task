package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_5253 5253. 找到指定长度的回文数
 * @Description: https://leetcode-cn.com/contest/weekly-contest-286/problems/find-palindrome-with-fixed-length/
 * @date 2022/3/27 10:44
 */
public class task_5253 {

    public static void main( String[] args ) {
        /*int[] queries = {94,18,388481008,38,16017953,16,223505660,78,123699557,346244579,2};
        int intLength = 8;*/
        int[] queries = {9,201429812,8,520498110,492711727,339882032,462074369,9,7,6};
        int intLength = 4;
        long[] longs = kthPalindrome2( queries, intLength );
        System.out.println(longs);
    }



    public static long[] kthPalindrome2(int[] queries, int intLength) {
        boolean isOdd = intLength % 2 != 0;
        int half = intLength/2;
        if(isOdd){
            half++;
        }
        long min = (long) Math.pow( 10,half-1 );
        long max = (long) (Math.pow( 10,half ) - 1);
        long[] res = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            if (min+query>max) {
                res[i] = -1;
                continue;
            }
            res[i] = make(min+query,isOdd);
        }
        return res;
    }

    private static long make( long l, boolean isOdd ) {
        String str = String.valueOf( l );
        StringBuilder sb = new StringBuilder(str);
        long val = 0;
        if (isOdd) {
            val = Long.parseLong( str += sb.reverse().substring( 1 ) );
        }else{
            val = Long.parseLong( str += sb.reverse() );
        }
        return val;
    }

    public static long[] kthPalindrome(int[] queries, int intLength) {
        long start = (long) Math.pow( 10, intLength - 1 );
        long end = (long) (Math.pow( 10, intLength ));
        long[] res = new long[queries.length];
        if (queries.length==0) {
            return new long[0];
        }
        long max = Long.MIN_VALUE;
        for (int query : queries) {
            if (query<=end-start && query>max) {
                max = query;
            }
        }
        List<Long> list = new ArrayList<>();
        for (long i = 0; i < end - start; i++) {
            long num = start + i;
            if (isPalindrome(num)) {
               list.add( num );
                if (list.size()>=max+1) {
                    break;
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            long val = -1;
            if (queries[i]-1<list.size()) {
                val = list.get( queries[i]-1 );
            }
            res[i] = val;
        }
        return res;
    }

    private static boolean isPalindrome( long x ) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            int l = (int) (x / div);
            int r = (int) (x % 10);
            if (l != r) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
}
