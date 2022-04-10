package com.wp;

/**
 * @author: wp
 * @Title: task_780  780. 到达终点
 * @Description: https://leetcode-cn.com/problems/reaching-points/
 * @date 2022/4/9 12:19
 */
public class task_780 {

    public static void main( String[] args ) {
        System.out.println( reachingPoints2( 1, 1,1000000000, 1 ) );
    }

    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx==tx && sy==ty) {
            return true;
        }
        boolean flag = false;
        if (sx+sy <= tx) {
            flag = reachingPoints( sx+sy, sy, tx, ty );
        }
        if (!flag && sx+sy <= ty) {
            flag = reachingPoints( sx, sx+sy, tx, ty );
        }
        return flag;
    }

    public static boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        if (sx==tx && sy==ty) {
            return true;
        }
        boolean flag = false;
        if (tx-ty>=sx && tx-ty!=tx) {
            flag = reachingPoints( sx, sy, tx-ty, ty );
        }
        if (!flag && ty-tx>=sy && ty-tx != ty) {
            flag = reachingPoints( sx, sy, tx, ty-tx );
        }
        return flag;
    }
}
