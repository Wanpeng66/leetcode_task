package com.wp;

import java.util.*;

/**
 * @author: wp
 * @Title: task_997  997. 找到小镇的法官
 * @Description: https://leetcode-cn.com/problems/find-the-town-judge/
 * @date 2022/2/15 15:32
 */
public class task_997 {

    public static void main( String[] args ) {
        findJudge( 2,new int[][]{{1,2}} );
    }
    public static int findJudge(int n, int[][] trust) {
        int[] in = new int[n+1];
        int[] out = new int[n+1];
        //计算每个顶点的入度和出度
        for (int[] ints : trust) {
            in[ints[1]]++;
            out[ints[0]]++;
        }
        for (int i = 1; i <= n; i++) {
            //有n-1个人相信他 而且他不相信任何人
            if (in[i]==n-1 && out[i]==0) {
                return i;
            }
        }
        return -1;
    }
}
