package com.wp;

/**
 * @author: wp
 * @Title: task_468  468. 验证IP地址
 * @Description: https://leetcode.cn/problems/validate-ip-address/
 * @date 2022/5/29 20:53
 */
public class task_468 {
    public static void main( String[] args ) {
        System.out.println( validIPAddress( "1.0.1." ) );
    }
    public static String validIPAddress(String queryIP) {
        String reg = "^[0-9]*$";
        String[] array;
        if (queryIP.contains( "." )) {
            int i = queryIP.length() - queryIP.replaceAll( "\\.", "" ).length();
            if (i!=3) {
                return "Neither";
            }
            array = queryIP.split( "\\." );
            if (array.length!=4) {
                return "Neither";
            }
            for (i = 0; i < array.length; i++) {
                String str = array[i];
                int length = str.length();
                if (length ==0 || ( length >1 && str.startsWith( "0" )) || length >4 || !str.matches( reg ) || Integer.parseInt( str )>=256) {
                    return "Neither";
                }
            }
            return "IPv4";
        }
        int i = queryIP.length() - queryIP.replaceAll( ":", "" ).length();
        if (i!=7) {
            return "Neither";
        }
        array = queryIP.split( ":" );
        if (array.length!=8) {
            return "Neither";
        }
        reg = "^[0-9a-fA-F]*$";
        for (i = 0; i < array.length; i++) {
            String str = array[i];
            if ( str.length()==0 || str.length()>4 || !str.matches( reg )) {
                return "Neither";
            }
        }
        return "IPv6";
    }
}
