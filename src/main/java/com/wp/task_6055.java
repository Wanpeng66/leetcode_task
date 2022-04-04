package com.wp;

/**
 * @author: wp
 * @Title: task_6055   6055. 转化时间需要的最少操作数
 * @Description: https://leetcode-cn.com/contest/weekly-contest-287/problems/minimum-number-of-operations-to-convert-time/
 * @date 2022/4/3 10:31
 */
public class task_6055 {
    public static void main( String[] args ) {
        System.out.println( convertTime( "00:00", "00:00" ) );
    }
    public static int convertTime(String current, String correct) {
        String[] currentArray = current.split( ":" );
        String[] correctArray = correct.split( ":" );
        int currentM = Integer.parseInt( currentArray[0] )* 60 +Integer.parseInt( currentArray[1] );
        int cM = Integer.parseInt( correctArray[0] )*60 + Integer.parseInt( correctArray[1] );
        int[] add = {60,15,5,1,0};
        int num = 0;
        big:while (true) {
            for (int i = 0; i < add.length; i++) {
                int sum = currentM + add[i];
                if (sum<cM) {
                    currentM = sum;
                    num++;
                    continue big;
                } else if (sum>cM) {
                    continue;
                }else{
                    if (add[i]!=0) {
                        num++;
                    }
                    break big;
                }

            }
        }
        return num;
    }
}
