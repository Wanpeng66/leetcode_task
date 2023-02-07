package com.wp;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class task_1604 {

    public static void main(String[] args) {
        String[] keyName = {"daniel","daniel","daniel","luis","luis","luis","luis"};
        String[] keyTime = {"10:00","10:40","11:00","09:00","11:00","13:00","15:00"};
        List<String> alertNames = alertNames(keyName, keyTime);
        System.out.println(alertNames);
    }
    public static List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String,List<Integer>> name2time = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            List<Integer> list = name2time.getOrDefault(keyName[i], new ArrayList<>());
            String time = keyTime[i];
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            list.add(hour * 60 * 60 + minute * 60);
            name2time.putIfAbsent(keyName[i], list);
        }

        Iterator<Map.Entry<String, List<Integer>>> iterator = name2time.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<Integer>> entry = iterator.next();
            List<Integer> timeList = entry.getValue();
            filter(iterator, timeList);
        }
        return name2time.keySet().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    }

    private static void filter(Iterator<Map.Entry<String, List<Integer>>> iterator, List<Integer> timeList) {
        if (timeList.size()<3) {
            iterator.remove();
        }
        timeList.sort(Comparator.naturalOrder());
        int left = 0,right = 0;
        boolean shouldRemove = true;
        while (right< timeList.size()) {
            if (timeList.get(right)-timeList.get(left)<=3600 || left==right) {
                if (right - left >= 2) {
                    shouldRemove = false;
                    break;
                }
                right++;
            } else {
                left++;
            }

        }
        if (shouldRemove) {
            iterator.remove();
        }
    }
}
