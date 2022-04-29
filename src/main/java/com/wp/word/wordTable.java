package com.wp.word;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: wp
 * @Title: wordTable
 * @Description:
 * @date 2022/4/25 13:22
 */
public class wordTable {
    public static void main( String[] args ) {
        List<String> lists = tableInWord( "F:\\file\\武器.doc" );
        System.out.println( lists );
    }
    /**
     * 读取文档中表格
     * @param filePath 文档路径
     */
    public static List<String> tableInWord( String filePath){
        try{
            FileInputStream in = new FileInputStream(filePath);
            if(filePath.toLowerCase().endsWith("docx")){
                XWPFDocument xwpf = new XWPFDocument(in);
                Iterator<XWPFTable> it = xwpf.getTablesIterator();
                List<String> res = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                while(it.hasNext()){
                    XWPFTable table = it.next();
                    sb.append( "<table>" );
                    List<XWPFTableRow> rows = table.getRows();
                    //读取每一行数据
                    for (int i = 0; i < rows.size(); i++) {
                        XWPFTableRow row = rows.get(i);
                        //读取每一列数据
                        List<XWPFTableCell> cells = row.getTableCells();
                        sb.append( "<tr>" );
                        for (int j = 0; j < cells.size(); j++) {
                            XWPFTableCell cell = cells.get(j);
                            sb.append( "<td>"+cell.getText()+"</td>" );
                        }
                        sb.append( "</tr>" );
                    }
                    sb.append( "</table>" );
                    res.add( sb.toString() );
                    sb.setLength( 0 );
                    break;
                }
                return res;
            }
            else{
                POIFSFileSystem pfs = new POIFSFileSystem(in);
                HWPFDocument hwpf = new HWPFDocument(pfs);
                Range range = hwpf.getRange();
                TableIterator it = new TableIterator(range);
                List<String> res = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                while (it.hasNext()) {
                    Table tb = it.next();
                    sb.append( "<table>" );
                    for (int i = 0; i < tb.numRows(); i++) {
                        TableRow tr = tb.getRow(i);
                        sb.append( "<tr>" );
                        //迭代列，默认从0开始
                        for (int j = 0; j < tr.numCells(); j++) {
                            TableCell td = tr.getCell(j);//取得单元格
                            //取得单元格的内容
                            for(int k = 0; k < td.numParagraphs(); k++){
                                Paragraph para = td.getParagraph(k);
                                String s = para.text();
                                //去除后面的特殊符号
                                if(null != s && !"".equals(s)){
                                    s = s.substring(0, s.length()-1);
                                }
                                sb.append( "<td>"+s+"</td>" );
                            }
                        }
                        sb.append( "</tr>" );
                    }
                    sb.append( "</table>" );
                    res.add( sb.toString() );
                    sb.setLength( 0 );
                }
                return res;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
