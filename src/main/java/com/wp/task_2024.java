package com.wp;

/**
 * @author: wp
 * @Title: task_2024  2024. 考试的最大困扰度
 * @Description: https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/
 * @date 2022/3/29 19:21
 */
public class task_2024 {
    public static void main( String[] args ) {
        System.out.println( maxConsecutiveAnswers( "TTFTTFTT", 1 ) );
    }


    public static int maxConsecutiveAnswers(String answerKey, int _k) {
        char[] chars = answerKey.toCharArray();
        //T字符的最大长度
        int tNum = maxChar( chars, _k, 'T' );
        //F字符的最大长度
        int fNum = maxChar( chars, _k, 'F' );
        return Math.max( tNum, fNum );
    }

    private static int maxChar( char[] chars, int k, char c ) {
        //res为滑动窗口的长度
        int res = 0;
        //notNum为!=c的字符数量
        int notNum = 0;
        for (int start = 0, end = 0; end < chars.length; end++) {
            if (chars[end]!=c) {
                notNum++;
            }
            // 因为最多改动k次，所以当notNum>k时 需要将左端点往右移动
            // 尝试满足notNum<k的条件 待条件满足后右端点继续往右查找
            while (notNum>k) {
                if (chars[start++]!=c) {
                    notNum--;
                }
            }
            int x = end - start + 1;
            //如果这次滑动窗口的长度大于之前的 那就记录大值
            if (x>res) {
                res = x;
            }
        }
        return res;
    }

}
