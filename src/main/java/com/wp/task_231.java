package com.wp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_231 231. 2 的幂
 * @Description: https://leetcode-cn.com/problems/power-of-two/
 * @date 2022/1/28 13:12
 */
public class task_231 {



    Map<String,Boolean> map = new HashMap<>();
    public boolean isPowerOfTwo(int n) {
        if (n==1) {
            return true;
        }
        if (n==0||n%2!=0) {
            return false;
        }
        n = n/2;
        if (map.containsKey( n+"" )) {
            return map.get( n+"" );
        }
        boolean flag = isPowerOfTwo( n );
        map.put( n+"",flag );
        return flag;
    }

}
