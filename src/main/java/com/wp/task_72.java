package com.wp;

/**
 * @author: wp
 * @Title: task_72  72. 编辑距离
 * @Description: https://leetcode-cn.com/problems/edit-distance/
 * @date 2022/2/22 16:34
 */
public class task_72 {
    public static void main( String[] args ) {
        System.out.println( minDistance2( "mitcmu", "mtacnu" ) );
    }



    public static int minDistance2(String word1, String word2) {
        if ("".equals( word1 )) {
            return word2.length();
        }
        if ("".equals( word2 )) {
            return word1.length();
        }
        char[] a1 = word1.toCharArray();
        char[] a2 = word2.toCharArray();
        int[][] dp = new int[a1.length][a2.length];
        for (int i = 0; i < a2.length; i++) {
            if (a1[0]==a2[i]) {
                dp[0][i] = i;
            } else if (i!=0) {
                dp[0][i] = dp[0][i-1] + 1;
            }else{
                dp[0][i] = 1;
            }
        }
        for (int i = 0; i < a1.length; i++) {
            if (a1[i]==a2[0]) {
                dp[i][0] = i;
            } else if (i!=0) {
                dp[i][0] = dp[i-1][0] +1;
            }else{
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < a1.length; i++) {
            for (int j = 1; j < a2.length; j++) {
                if (a1[i]!=a2[j]) {
                    dp[i][j] = Math.min(Math.min( dp[i-1][j]+1, dp[i][j-1]+1) , dp[i-1][j-1]+1  );
                }else{
                    dp[i][j] = Math.min(Math.min( dp[i-1][j]+1, dp[i][j-1]+1) , dp[i-1][j-1]  );
                }
            }
        }
        return dp[a1.length-1][a2.length-1];
    }

    public static int minDistance(String word1, String word2) {
        char[] a1 = word1.toCharArray();
        char[] a2 = word2.toCharArray();
        int[] min = {Math.max( word1.length(),word2.length() )};
        minDistance( a1, a2, 0, 0, 0, min );
        return min[0];
    }

    private static void minDistance( char[] a1, char[] a2,  int o1, int o2,int d, int[] min ) {
        if (o1>=a1.length || o2>=a2.length) {
            if (o1>=a1.length && o2<a2.length) {
                d += a2.length-o2;
            }
            if (o2>=a2.length && o1<a1.length) {
                d += a1.length-o1;
            }
            if (d<min[0]) {
                min[0] = d;
            }
            return;
        }
        if (a1[o1]==a2[o2]) {
            minDistance( a1, a2, o1+1, o2+1, d, min );
        }else{
            //a1修改一个字符
            minDistance( a1, a2, o1+1, o2+1, d+1, min );
            //a1插入一个字符
            minDistance( a1, a2, o1, o2+1, d+1, min );
            //a1删除一个字符
            minDistance( a1, a2, o1+1, o2, d+1, min);
        }

    }
}
