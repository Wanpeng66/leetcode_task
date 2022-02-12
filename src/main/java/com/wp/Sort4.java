package com.wp;

/**
 * @author: wp
 * @Title: Sort4
 * @Description:
 * @date 2022/2/3 14:11
 */
public class Sort4 {


    public static void main( String[] args ) {
        int[] array = {-1,-3,0,10};

        long[] phones = {187177924121l,187177824121l,157177924121l,187177924122l};
        countingSort(phones,10);
        //countingSort( array );
        System.out.println( phones );
    }

    private static void countingSort( long[] phones, int i ) {
        if (i<0) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (Long phone : phones) {
            int val = getValue(phone,i);
            if (val>max) {
                max = val;
            }
        }
        int[] tmp = new int[++max];
        for (long phone : phones) {
            int val = getValue( phone, i );
            tmp[val] += 1;
        }

        for (int j = 1; j < tmp.length; j++) {
            tmp[j] += tmp[j-1];
        }
        long[] res = new long[phones.length];
        for (int j = phones.length - 1; j >= 0; j--) {
            long phone = phones[j];
            int val = getValue( phone, i );
            int offset = tmp[val];
            res[--offset] = phone;
            tmp[val] = offset;
        }
        for (int j = 0; j < res.length; j++) {
            phones[j] = res[j];
        }
        countingSort( phones, i-1 );
    }

    private static int getValue( Long phone, int i ) {
        char c = String.valueOf( phone ).charAt( i );
        return Character.getNumericValue( c );
    }

    private static void countingSort( int[] array ) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean isAdd = false;
        for (int i : array) {
            if (i<min){
                min = i;
                continue;
            }
            if (i>max) {
                max = i;
            }
        }
        if (min<0) {
            isAdd = true;
            max -= min;
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] - min;
            }
        }
        int[] tmp = new int[++max];
        for (int i = 0; i < array.length; i++) {
            int offset = array[i];
            tmp[offset] = tmp[offset]+1;
        }

        for (int i = 1; i < tmp.length; i++) {
            tmp[i] = tmp[i]+tmp[i-1];
        }

        int[] res = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int score = array[i];
            int offset = tmp[score];
            tmp[score] = --offset;
            if (isAdd) {
                score += min;
            }
            res[offset] = score;
        }
    }
}
