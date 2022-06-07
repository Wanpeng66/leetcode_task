package com.wp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wp
 * @Title: task_732  732. 我的日程安排表 III
 * @Description: https://leetcode.cn/problems/my-calendar-iii/
 * @date 2022/6/6 15:50
 */
public class task_732 {
    public static void main( String[] args ) throws IOException {
        /*MyCalendarThree calendarThree = new MyCalendarThree();
        System.out.println( calendarThree.book( 10, 20 ) );
        System.out.println( calendarThree.book( 50, 60 ) );
        System.out.println( calendarThree.book( 10, 40 ) );
        System.out.println( calendarThree.book( 5, 15 ) );
        System.out.println( calendarThree.book( 5, 10 ) );
        System.out.println( calendarThree.book( 25, 55 ) );*/
        PDDocument document = PDDocument.load(new File("D:\\work\\script\\tmp\\1.pdf"));
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();

    }
    static class MyCalendarThree {
        Map<Integer,Integer> map;
        public MyCalendarThree() {
            map = new HashMap<>();
        }

        public int book(int start, int end) {
            int num = 0;
            for(int i=start;i<end;i++){
                Integer orDefault = map.getOrDefault( i, 0 );
                int value = orDefault + 1;
                if (value>num) {
                    num = value;
                }
                map.put( i, value );
            }
            return num;
        }
    }

}

