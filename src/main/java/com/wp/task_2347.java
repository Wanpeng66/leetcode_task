package com.wp;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class task_2347 {
    public static void main(String[] args) {
        int[] ranks = {13,2,3,1,9};
        char[] suits = {'a','a','a','a','a'};
        System.out.println(bestHand(ranks, suits));
    }
    public static String bestHand(int[] ranks, char[] suits) {
        String res = "High Card";
        Map<String, Map<String,Integer>> map = new LinkedHashMap<>();
        for (int i = 0; i < ranks.length; i++) {
            int rank = ranks[i];
            char suit = suits[i];
            Map<String, Integer> subMap = map.getOrDefault("Flush", new HashMap<>());
            process(subMap, String.valueOf(suit));
            map.putIfAbsent("Flush", subMap);
            subMap = map.getOrDefault("num", new HashMap<>());
            process(subMap, String.valueOf(rank));
            map.putIfAbsent("num", subMap);
        }
        for (String key : map.keySet()) {
            Map<String, Integer> subMap = map.get(key);
            Integer max = subMap.get("max");
            if (key.equals("Flush") && max==5) {
                res = key;
            } else if (key.equals("num") && max>=3) {
                res = "Three of a Kind";
            } else if (key.equals("num") && max>=2) {
                res = "Pair";
            }
        }
        return res;
    }

    private static void process(Map<String, Integer> map, String key) {
        Integer num = map.getOrDefault(key, 0);
        Integer max = map.getOrDefault("max", 0);
        if (num+1>max) {
            map.put("max", num+1);
        }
        map.put(key, num+1);
    }
}
