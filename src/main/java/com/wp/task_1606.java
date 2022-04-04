package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_1606  1606. 找到处理最多请求的服务器
 * @Description: https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/
 * @date 2022/3/30 20:16
 */
public class task_1606 {
    public static void main( String[] args ) {
        int k = 22;
        int[] arrival = {2,4,6,10,11,13,14,15,16,18,20,21,26,27,31,32,33,35,36,37,40,46,47,48,49,56,59,62,63,66,71,74,75,76,77,78,79,81,84,86,87,88,91,92,93,94,95,96,98};
        int[] load = {6,43,69,74,23,70,68,39,15,17,72,71,8,71,53,75,9,48,96,42,43,4,81,92,14,20,7,61,60,8,18,4,13,14,20,77,81,96,52,38,91,71,65,13,77,19,82,30,54};
        List<Integer> list = busiestServers( k, arrival, load );
        System.out.println( list );
    }

    public static List<Integer> busiestServers2(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<Integer>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int[] requests = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                available.add(busy.poll()[1]);
            }
            if (available.isEmpty()) {
                continue;
            }
            Integer p = available.ceiling(i % k);
            if (p == null) {
                p = available.first();
            }
            requests[p]++;
            busy.offer(new int[]{arrival[i] + load[i], p});
            available.remove(p);
        }
        int maxRequest = Arrays.stream(requests).max().getAsInt();
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequest) {
                ret.add(i);
            }
        }
        return ret;
    }

    public static List<Integer> busiestServers( int k, int[] arrival, int[] load) {
        PriorityQueue<Integer[]> inUse = new PriorityQueue<>( ( o1, o2 ) -> o1[1]-o2[1] );
        TreeSet<Integer> unUseSet = new TreeSet<>();
        boolean[] unUse = new boolean[k];
        for (int i = 0; i < k; i++) {
            unUse[i] = true;
            unUseSet.add( i );
        }
        int[] nums = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            int server = i % k;
            if (unUse[server]) {
                unUse[server] = false;
                unUseSet.remove( server );
                Integer[] array = new Integer[2];
                array[0] = server;array[1] = arrival[i]+load[i];
                inUse.offer( array );
                nums[server]++;
            }else{
                while (!inUse.isEmpty()) {
                    Integer[] peek = inUse.peek();
                    if (peek[1]<=arrival[i]) {
                        Integer n = peek[0];
                        unUse[n] = true;
                        unUseSet.add( n );
                        inUse.poll();
                    }else{
                        break;
                    }
                }
                if (unUseSet.isEmpty()) {
                    continue;
                }
                Integer offset = unUseSet.ceiling( server );
                if (null==offset) {
                    offset = unUseSet.first();
                }
                unUseSet.remove( offset );
                unUse[offset] = false;
                inUse.offer( new Integer[]{offset, arrival[i]+load[i]} );
                nums[offset]++;
            }
        }
        int max = Arrays.stream(nums).max().getAsInt();
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            if (nums[i] == max) {
                ret.add(i);
            }
        }
        return ret;
    }

    private static List<Integer> method1( int k, int[] arrival, int[] load ) {
        int[][] servers = new int[k][2];
        for (int i = 0; i < k; i++) {
            int[] array = new int[2];
            array[0] = i;
            servers[i] = array;
        }
        int[] nums = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            System.out.println("---------------------------");
            System.out.println("给第"+i+"个任务安排服务器");
            int server = i % k;
            for (int j = 0; j < k; j++) {
                int tmp = (server + j) % k;
                if (servers[tmp][1]<= arrival[i]) {
                    servers[tmp][1] = arrival[i] + load[i];
                    nums[tmp]++;
                    System.out.println("将任务分给第"+tmp+"个服务器");
                    break;
                }
                System.out.println("第"+tmp+"个服务器忙，寻找下一个服务器");
            }
        }
        int maxRequest = Arrays.stream(nums).max().getAsInt();
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            if (nums[i] == maxRequest) {
                ret.add(i);
            }
        }
        return ret;
    }
}
