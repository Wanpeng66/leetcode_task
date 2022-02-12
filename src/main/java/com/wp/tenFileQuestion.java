package com.wp;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author: wp
 * @Title: tenFileQuestion
 * @Description:
 * @date 2022/2/2 12:52
 */
public class tenFileQuestion {
    static int num = 5;
    public static void main( String[] args ) {
        fileMethod();
    }

    private static void fileMethod() {
        String nPath = "D:\\tmp\\test\\full.txt";
        String path = "C:\\Users\\plantdata-nb-0001\\Desktop\\test";

        File root = new File( path );
        if (null==root) {
            return;
        }
        File[] files = root.listFiles();
        if (0== files.length) {
            return;
        }
        Queue<Integer>[] tmps = new Queue[files.length];
        BufferedReader[] readers = new BufferedReader[files.length];
        int k = 0;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            tmps[i] = new LinkedList();
            try {
                BufferedReader reader = new BufferedReader( new FileReader( file ) );
                readers[i] = reader;
                while (k<num) {
                    String str = reader.readLine();
                    if (null==str) {
                        break;
                    }
                    if (!StringUtils.hasText( str )) {
                        continue;
                    }
                    tmps[i].add( Integer.valueOf( str ) );
                    k++;
                }
                k = 0;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter( Paths.get( new File( nPath ).toURI()  ), Charset.forName( "UTF-8" ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer[] array = new Integer[tmps.length];
        int wNum = 0;
        while (true) {
            for (int i = 0; i < tmps.length; i++) {
                Queue<Integer> tmp = tmps[i];
                if (null==tmp) {
                    continue;
                }
                if (tmp.isEmpty() && reInitial( tmp, readers[i] )) {
                    tmps[i] = null;
                    tmp = null;
                }
                if(null==array[i] && null!=tmp){
                    array[i] = tmps[i].poll();
                }
            }
            int offset = getMin(array);
            if (offset==-1) {
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            try {
                writer.write( array[offset] );
                writer.newLine();
                if (wNum%num==0) {
                    writer.flush();
                }
                wNum++;
                array[offset] = null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static int getMin( Integer[] array ) {
        int offset = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (null!=array[i]&&array[i]<min) {
                offset = i;
                min = array[i];
            }
        }
        return offset;
    }

    private static boolean reInitial( Queue<Integer> tmp, BufferedReader reader ) {
        int k = 0;
        while (k<num) {
            String str = null;
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null==str) {
                //代表文件读取完毕
                return true;
            }
            if (!StringUtils.hasText( str )) {
                continue;
            }
            tmp.add( Integer.valueOf( str ) );
            k++;
        }
        return false;
    }

}
