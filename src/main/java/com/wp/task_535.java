package com.wp;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author: wp
 * @Title: task_535  535. TinyURL 的加密与解密
 * @Description:  https://leetcode-cn.com/problems/encode-and-decode-tinyurl/
 * @date 2022/4/11 11:33
 */
public class task_535 {
    public static void main( String[] args ) {
        String encode = encode( "https://leetcode.com/problems/design-tinyurl" );
        System.out.println( encode );
        System.out.println( decode( encode ) );
    }

    static Map<String,String> long2short = new HashMap<>();
    static Map<String,String> short2long = new HashMap<>();
    static Random random = new Random();
    // Encodes a URL to a shortened URL.
    public static String encode(String longUrl) {
        if (long2short.containsKey( longUrl )) {
            return long2short.get( longUrl );
        }
        String str = longUrl;
        str = str.concat( random.nextInt()+"" );
        while (short2long.containsKey( str.hashCode()+"" )) {
            str = str.concat( random.nextInt()+"" );
        }
        short2long.put( str.hashCode()+"", longUrl );
        long2short.put( longUrl, str.hashCode()+"" );
        return "http://tinyurl.com/"+str.hashCode();
    }

    // Decodes a shortened URL to its original URL.
    public static String decode(String shortUrl) {
        shortUrl = shortUrl.replace( "http://tinyurl.com/","" );
        return short2long.get( shortUrl );
    }
}
