package com.wp;

import java.util.HashMap;
import java.util.Map;

public class task_1124 {

    public static int longestWPI(int[] hours) {
        int cur = 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) {
                cur++;
            } else {
                cur--;
            }
            if (cur > 0) {
                res = i + 1;
            } else {
                if (!map.containsKey(cur)) {
                    map.put(cur, i);
                }
                if (map.containsKey(cur - 1)) {
                    res = Math.max(res, i - map.get(cur - 1));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] hours = {9,9,6,6,9};
        System.out.println(longestWPI(hours));
    }
}
