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
        List<Integer> needs = Arrays.asList( 2,2,1 );
        List<List<Integer>> special = new ArrayList<>();
        special.add( Arrays.asList( 1,1,0,4 ) );
        special.add( Arrays.asList( 2,2,1,9 ) );
        long l = System.currentTimeMillis();
        int i = shoppingOffers2( price, special, needs );
        System.out.println(System.currentTimeMillis()-l);
        System.out.println( i );

    }
    static List<List<Integer>> policy = null;
    public static int shoppingOffers2(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int size = price.size();
        //去除掉比单独买相同数量商品还贵的大礼包
        Iterator<List<Integer>> iterator = special.iterator();
        while (iterator.hasNext()) {
            List<Integer> list = iterator.next();
            long sum = 0l;
            for (int i = 0; i < list.size()-1; i++) {
                Integer num = list.get( i );
                sum += price.get( i )*num;
            }
            if (sum<list.get( size )) {
                iterator.remove();
            }
        }
        //将单独买一件商品也转换成大礼包
        for (int i = 0; i < size; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (i==j) {
                    list.add( j,1 );
                }else{
                    list.add( j,0 );
                }
            }
            list.add( size,price.get( i ) );
            special.add( list );
        }
        long[] total = {Long.MAX_VALUE};
        ArrayList<Integer> cNeeds = new ArrayList();
        Map<ArrayList<Integer>,Long> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            cNeeds.add( i,0 );
        }
        shoppingOffers(special,needs,0,cNeeds,total,new ArrayList<>(),map);
        return (int) total[0];
    }

    private static void shoppingOffers(  List<List<Integer>> special, List<Integer> needs,long currentSum,ArrayList<Integer> cNeeds,long[] total, List<List<Integer>> cPolicy,Map<ArrayList<Integer>,Long> map ) {
        boolean isDone = true;
        for (int i = cNeeds.size() - 1; i >= 0; i--) {
            if (cNeeds.get( i )!=needs.get( i )) {
                isDone = false;
                break;
            }
        }
        if (isDone && total[0]>currentSum) {
            total[0] = currentSum;
            policy = new ArrayList<>(cPolicy);
            return;
        }
        for (int i = 0; i < special.size(); i++) {
            List<Integer> list = special.get( i );
            Integer sum = list.get( list.size() - 1 );
            boolean flag = true;
            if (total[0]!=Long.MAX_VALUE && currentSum+sum>total[0]) {
                flag = false;
            }else{
                for (int j = 0; j < list.size()-1; j++) {
                    if (cNeeds.get( j )+list.get( j )>needs.get( j )) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                for (int j = 0; j < list.size()-1; j++) {
                    cNeeds.set( j,cNeeds.get( j )+list.get( j ) );
                }
                currentSum += list.get( list.size()-1 );
                if (!map.containsKey( cNeeds) || map.get( cNeeds )>currentSum) {
                    int size = cPolicy.size();
                    cPolicy.add( size,list );
                    map.put( cNeeds,currentSum );
                    shoppingOffers( special,needs,currentSum,cNeeds,total,cPolicy,map );
                    cPolicy.remove( size );
                }
                for (int j = 0; j < list.size()-1; j++) {
                    cNeeds.set( j,cNeeds.get( j )-list.get( j ) );
                }
                currentSum -= list.get( list.size()-1 );

            }
        }
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
