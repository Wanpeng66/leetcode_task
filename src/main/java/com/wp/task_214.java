package com.wp;
/**
 * @author: wp
 * @Title: task_214  214. 最短回文串
 * @Description:  https://leetcode-cn.com/problems/shortest-palindrome/
 * @date 2022/4/11 10:58
 */
public class task_214 {
    public static void main( String[] args ) {
        String str = "abcd";
        System.out.println( shortestPalindrome( str ) );
    }

    static String[] array = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    public static String shortestPalindrome(String s) {
        String str = s;
        int length = str.length();
        String half = str.substring( 0, length/2 );
        String otherHalf = "";
        if (length%2==0) {
            otherHalf = str.substring( length/2 );
        }else{
            otherHalf = str.substring( length/2+1 );
        }
        if (half.hashCode()==new StringBuilder(otherHalf).reverse().toString().hashCode()) {
            return str;
        }
        for (int i = 0; i < array.length; i++) {
            str = array[i].concat( str );
            String res = shortestPalindrome( str );
            if (null!=res) {
                return res;
            }
        }
        return null;
    }
}
