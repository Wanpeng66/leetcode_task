package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_638  638. 大礼包
 * @Description: https://leetcode-cn.com/problems/shopping-offers/
 * @date 2022/2/21 15:53
 */
public class task_638 {



    public static void main( String[] args ) {
        List<Integer> price = Arrays.asList( 0,0,0 );
        List<Integer> needs = Arrays.asList( 1,1,0 );
        List<List<Integer>> special = new ArrayList<>();
        special.add( Arrays.asList( 1,1,0,4 ) );
        special.add( Arrays.asList( 2,2,1,9 ) );
        System.out.println( shoppingOffers( price, special, needs ) );

    }

    static List<Integer> Price ;
    public static int shoppingOffers( List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Price = price;
        int[] cur = new int[needs.size()];
        Long[] min = {Long.MAX_VALUE};
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i)==0) {
                Iterator<List<Integer>> iterator = special.iterator();
                while (iterator.hasNext()) {
                    List<Integer> next = iterator.next();
                    if (next.get( i )!=0) {
                        iterator.remove();
                    } else {
                        long sum = 0l;
                        for (int j = 0; j < next.size()-1; j++) {
                            sum += (next.get( j )*price.get( j ));
                        }
                        if (next.get( next.size()-1 )>sum) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        calculateMoney(special,cur,needs.toArray(new Integer[0]),array,min );
        return min[0].intValue();
    }

    private static void calculateMoney( List<List<Integer>> special, int[] cur, Integer[] needs, List<Integer> array, Long[] min ) {
        int size = array.size();
        for (int i = 0; i < needs.length; i++) {
            if (cur[i]>needs[i]) {
                return;
            }
        }
        for (int i = 0; i < special.size(); i++) {
            array.add( array.size(),i );
            List<Integer> list = special.get( i );
            for (int j = 0; j < cur.length; j++) {
                cur[j] += list.get( j );
            }
            calculateMoney( special,cur,needs,array, min );
            for (int j = 0; j < cur.length; j++) {
                cur[j] -= list.get( j );
            }
            array = array.subList( 0,size );
        }
        long tmpTotal = 0l;
        for (Integer offset : array) {
            List<Integer> spe = special.get( offset );
            tmpTotal += spe.get( spe.size()-1 );
        }
        for (int i = 0; i < cur.length; i++) {
            tmpTotal += (needs[i]-cur[i])*Price.get( i );
        }
        if (tmpTotal<min[0]) {
            min[0] = tmpTotal;
        }

    }

}
