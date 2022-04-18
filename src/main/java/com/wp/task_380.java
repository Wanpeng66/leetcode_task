package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_380  380. O(1) 时间插入、删除和获取随机元素
 * @Description: https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 * @date 2022/4/13 19:10
 */
public class task_380 {

}
class RandomizedSet {
    Set<Integer> vals;
    List<Integer> list;
    Random random = new Random();

    public RandomizedSet() {
       vals = new HashSet<>();
       list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (!vals.contains( val )) {
            vals.add( val );
            list.add( val );
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if (vals.contains( val)) {
            vals.remove( val );
            list.remove( (Integer)val );
            return true;
        }
        return false;


    }

    public int getRandom() {
        int i = random.nextInt( vals.size() );
        return list.get( i );
    }
}