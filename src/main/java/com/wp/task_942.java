package com.wp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: wp
 * @Title: task_942  942. 增减字符串匹配
 * @Description: https://leetcode.cn/problems/di-string-match/
 * @date 2022/5/9 15:29
 */
public class task_942 {
    public static void main( String[] args ) {
        int[] match = diStringMatch( "DDIIDIDIID" );
        System.out.println( match );
    }
    public static int[] diStringMatch(String s) {
        char[] chars = s.toCharArray();
        boolean[] done = {false};
        int[] res = new int[s.length()+1];
        boolean[] used = new boolean[s.length()+1];
        for(int i=0;i<=s.length();i++){
            res[0] = i;
            used[i] = true;
            dfs(chars, 1, res, done, used);
            if (done[0]) {
                break;
            }
            used[i] = false;
        }
        return res;
    }

    private static void dfs( char[] chars, int i, int[] res, boolean[] done, boolean[] used ) {
        if (i>chars.length) {
            done[0] = true;
            return;
        }
        char aChar = chars[i-1];
        if (aChar=='I') {
            if (res[i-1]!=chars.length) {
                for(int j=res[i-1]+1;j<=chars.length;j++){
                    if (!used[j]) {
                        res[i] = j;
                        used[j] = true;
                        dfs( chars, i+1, res, done, used);
                        if (done[0]) {
                            break;
                        }
                        used[j] = false;
                    }
                }
            }
        } else if (aChar=='D') {
            if (res[i-1]!=0) {
                for(int j=res[i-1]-1;j>=0;j--){
                    if (!used[j]) {
                        res[i] = j;
                        used[j] = true;
                        dfs( chars, i+1, res, done, used);
                        if (done[0]) {
                            break;
                        }
                        used[j] = false;
                    }
                }
            }
        }
    }
}
