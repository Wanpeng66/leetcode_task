package com.wp;

/**
 * @author: wp
 * @Title: task_868  868. 二进制间距
 * @Description: https://leetcode-cn.com/problems/binary-gap/
 * @date 2022/4/24 12:36
 */
public class task_868 {
    public static void main( String[] args ) {
        System.out.println( binaryGap( 5 ) );
    }
    public static int binaryGap(int n) {
        String str = Integer.toBinaryString( n );
        char[] chars = str.toCharArray();
        int offset = -1;
        int gap = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if(aChar=='1'){
                if(offset!=-1 && i-offset>gap){
                    gap = i-offset;
                }
                offset = i;
            }
        }
        return gap;
    }
}
