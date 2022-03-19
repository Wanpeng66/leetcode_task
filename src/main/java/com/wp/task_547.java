package com.wp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author: wp
 * @Title: task_547  547. 省份数量
 * @Description: https://leetcode-cn.com/problems/number-of-provinces/
 * @date 2022/3/18 11:02
 */
public class task_547 {
    public static void main( String[] args ) throws Exception {
        /*String apk = "8n8K4P7N";
        String secret = "527fb3f2acc09d4323373a180d67925150761ea6";
        long timestamp = System.currentTimeMillis();
        System.out.println( timestamp );
        System.out.println( getSHA256StrJava( apk + timestamp + secret ) );*/
        /*int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println( findCircleNum( isConnected ) );*/
        Map<String, JSONObject> map = new HashMap<>();
        File root = new File( "C:\\Users\\wp\\Desktop\\赛迪需求数据" );
        File[] files = root.listFiles();
        for (File file : files) {
            String name = file.getName();
            String str = readJsonFile( file );
            JSONObject jsonData = JSONObject.parseObject( str );
            JSONArray values = jsonData.getJSONObject( "data" ).getJSONArray( "values" );
            JSONArray datas = new JSONArray();
            HashSet<String> cols = new HashSet<>();
            for (int i = 0; i < values.size(); i++) {
                JSONObject data = values.getJSONObject( i ).getJSONObject( "_source" );
                datas.add( data );
                cols.addAll( data.keySet() );
            }
            for (int i = 0; i < datas.size(); i++) {
                JSONObject data = datas.getJSONObject( i );
                Set<String> set = data.keySet();
                HashSet<String> hashSet = new HashSet<>( cols );
                hashSet.removeAll( set );
                for (String s : hashSet) {
                    data.put( s,null );
                }
            }
            JSONObject data = new JSONObject();
            data.put( "cols",cols );
            data.put( "datas",datas );
            map.put( name.replace( ".json","" ),data );
        }

        String[] array = map.keySet().toArray( new String[0] );
        for (int i = 0; i < array.length; i++) {
            WritableWorkbook workbook = Workbook.createWorkbook(new File( "F:\\file\\"+array[i]+".xls" ));
            WritableSheet sheet = workbook.createSheet("sheet", 0);
            JSONObject jsonData = map.get( array[i] );
            List<String> cols = new ArrayList<>(jsonData.getObject( "cols", HashSet.class ));
            JSONArray datas = jsonData.getJSONArray( "datas" );
            for (int j = 0; j < cols.size(); j++) {
                Label label = new Label( j,0,cols.get( j ) );
                sheet.addCell( label );
            }
            int row = 1;
            for (int j = 0; j < datas.size(); j++) {
                for (int k = 0; k < cols.size(); k++) {
                    JSONObject data = datas.getJSONObject( j );
                    String key = cols.get( k );
                    Object val = data.get( key );
                    String str = null==val?null:val.toString();
                    Label label = new Label( k,row,str );
                    sheet.addCell( label );
                }
                row++;
            }
            //开始执行写入操作
            workbook.write();
            //关闭流
            workbook.close();
        }


    }

    public static String readJsonFile(File jsonFile) {
        String jsonStr = "";
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
