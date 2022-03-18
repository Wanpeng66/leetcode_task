package com.wp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: wp
 * @Title: task_547  547. 省份数量
 * @Description: https://leetcode-cn.com/problems/number-of-provinces/
 * @date 2022/3/18 11:02
 */
public class task_547 {
    public static void main( String[] args ) {
        /*String apk = "8n8K4P7N";
        String secret = "527fb3f2acc09d4323373a180d67925150761ea6";
        long timestamp = System.currentTimeMillis();
        System.out.println( timestamp );
        System.out.println( getSHA256StrJava( apk + timestamp + secret ) );*/
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println( findCircleNum( isConnected ) );
    }

    public static int findCircleNum(int[][] isConnected) {
        int num = 0;
        boolean[] visited = new boolean[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                dfs(i,isConnected[i],visited, isConnected);
                num++;
            }
        }
        return num;
    }

    private static void dfs( int i, int[] ints, boolean[] visited, int[][] isConnected ) {
        visited[i] = true;
        for (int j = 0; j < ints.length; j++) {
            if (i!=j && ints[j]==1 && !visited[j]) {
                dfs( j,isConnected[j],visited,isConnected );
            }
        }
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
