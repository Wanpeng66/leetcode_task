package com.wp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wp
 * @Title: task_386  386. 字典序排数
 * @Description: https://leetcode-cn.com/problems/lexicographical-numbers/
 * @date 2022/4/18 15:19
 */
public class task_386 {
    public static void main( String[] args ) {
        List<Integer> list = lexicalOrder( 13 );
        System.out.println( list );
    }

    public static List<Integer> lexicalOrder( int n) {
        List<Integer> res = new ArrayList<>();
        for(int i=1;i<10;i++){
            if (i>n) {
                break;
            }
            res.add( i );
            dfs(i, n, res);
        }
        return res;
    }

    private static void dfs( int k, int n, List<Integer> res ) {
        for(int i=0;i<10;i++){
            int val = k*10 + i;
            if (val>n) {
                return;
            }
            res.add( val );
            dfs( val, n, res );
        }
    }
}
