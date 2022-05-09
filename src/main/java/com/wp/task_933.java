package com.wp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: wp
 * @Title: task_933  933. 最近的请求次数
 * @Description: https://leetcode-cn.com/problems/number-of-recent-calls/
 * @date 2022/5/6 16:44
 */
public class task_933 {
    class RecentCounter {
        List<Integer> data;
        public RecentCounter() {
            data = new ArrayList<>();
        }

        public int ping(int t) {
            Iterator<Integer> iterator = data.iterator();
            while(iterator.hasNext()){
                Integer next = iterator.next();
                if (next<t-3000) {
                    iterator.remove();
                }else{
                    break;
                }
            }
            data.add( t );
            return data.size();
        }
    }
}
