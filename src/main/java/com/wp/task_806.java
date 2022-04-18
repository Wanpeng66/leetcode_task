package com.wp;

/**
 * @author: wp
 * @Title: task_806  806. 写字符串需要的行数
 * @Description: https://leetcode-cn.com/problems/number-of-lines-to-write-string/
 * @date 2022/4/12 15:10
 */
public class task_806 {
    public static void main( String[] args ) {
        int[] widths = {4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String s = "bbbcccdddaaa";
        int[] ints = numberOfLines( widths, s );
        System.out.println( ints );
    }

    public static int[] numberOfLines(int[] widths, String s) {
        int total = 0;
        int line = 1;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            int width = widths[aChar - 'a'];
            if (total+width>100) {
                line++;
                total = width;
            }else{
                total += width;
            }
        }
        return new int[]{line, total};
    }
}
