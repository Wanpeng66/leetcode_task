package com.wp;


/**
 * @author: wp
 * @Title: task_09 回文数字
 * @Description: https://leetcode-cn.com/problems/palindrome-number/
 * @date 2022/1/24 11:15
 */
public class task_09 {

    public static void main( String[] args ) {
        int x = 121;
        System.out.println( isPalindrome( x ) );
    }

    public static boolean isPalindrome(int x) {
        String target = String.valueOf( x );
        char[] charArray = target.toCharArray();
        int length = charArray.length;
        int left = length/2 - 1;
        int right;
        if (length%2==0) {
            right = left + 1;
        }else{
            right = left + 2;
        }
        for (; left >= 0; left--) {
            if (charArray[left]!=charArray[right]) {
                return  false;
            }
            right++;
        }
        return true;
    }

    //反转字符串比对
    private boolean method01( int x ) {
        String target = String.valueOf( x );
        return target.equalsIgnoreCase( new StringBuilder(target).reverse().toString() );
    }
}
