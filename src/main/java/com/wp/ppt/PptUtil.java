package com.wp.ppt;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xslf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.xslf.usermodel.*;
import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;



/**
 * @author: wp
 * @Title: PptUtil
 * @Description:
 * @date 2022/4/24 15:59
 */
public class PptUtil {

    static int times = 4;
    public static void main( String[] args ) throws Exception {
        PPTtoImage( "F:\\file\\","611-科技QB产品化方案-v8.pptx" );
    }

    public static void PPTtoImage(String filePath, String fileName) throws Exception {
        File f = new File(filePath + fileName);
        if (f.exists()) {
            if (f.getName().endsWith(".pptx") || f.getName().endsWith(".PPTX")) {
                pptx2Image(filePath, fileName);
            } else {
                ppt2Image(filePath, fileName);
            }
        } else {
            throw new Exception("文档不存在");
        }
    }



    /**
     * 将pptx转成图片,保存在同一目录的image目录下
     *
     * @param filePath ppt文件路径
     * @param fileName ppt 文件名
     * @throws IOException
     */
    public static void pptx2Image(String filePath, String fileName) throws Exception {
        FileInputStream is  = new FileInputStream(filePath + fileName);
        XMLSlideShow    ppt = new XMLSlideShow(is);

        Dimension pgsize   = ppt.getPageSize();
        int       pageSize = ppt.getSlides().size();
        for (int i = 0; i < pageSize; i++) {
            for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
                if (shape instanceof XSLFTextShape) {
                    XSLFTextShape tsh = (XSLFTextShape) shape;
                    for (XSLFTextParagraph p : tsh) {
                        for (XSLFTextRun r : p) {
                            String fontFamily = "宋体";
                            r.setFontFamily(fontFamily);
                        }
                    }
                }
            }
            BufferedImage img      = new BufferedImage(pgsize.width*times, pgsize.height*times, BufferedImage.TYPE_INT_RGB);
            Graphics2D    graphics = img.createGraphics();
            graphics.setPaint(Color.white);
            graphics.scale(times, times);// 将图片放大times倍
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
            ppt.getSlides().get(i).draw(graphics);
            String pptname    = fileName.substring(0, fileName.lastIndexOf("."));
            String newimgPath = filePath + pptname+"_";
            String           file = newimgPath + (i + 1) + ".png";
            FileOutputStream out  = new FileOutputStream(file);
            javax.imageio.ImageIO.write(img, "png", out);
            IOUtils.closeQuietly(out);
        }
        IOUtils.closeQuietly(is);
    }
    /**
     * 将ppt转成图片,保存在同一目录的image目录下
     *
     * @param filePath ppt文件路径
     * @param fileName ppt 文件名
     * @throws IOException
     */
    public static void ppt2Image(String filePath, String fileName) throws IOException {
        HSLFSlideShow ppt = new HSLFSlideShow(new HSLFSlideShowImpl(filePath + fileName));
        Dimension pgsize = ppt.getPageSize();
        for (int i = 0; i < ppt.getSlides().size(); i++) {
            for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
                if (shape instanceof HSLFTextShape) {
                    HSLFTextShape tsh = (HSLFTextShape) shape;
                    for (HSLFTextParagraph p : tsh) {
                        for (HSLFTextRun r : p) {
                            String fontFamily = "宋体";
                            r.setFontFamily(fontFamily);
                        }
                    }
                }
            }
            BufferedImage img      = new BufferedImage(pgsize.width*times, pgsize.height*times, BufferedImage.TYPE_INT_RGB);
            Graphics2D    graphics = img.createGraphics();
            graphics.setPaint(Color.white);
            graphics.scale(times, times);// 将图片放大times倍
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
            ppt.getSlides().get(i).draw(graphics);
            String pptname    = fileName.substring(0, fileName.lastIndexOf("."));
            String newimgPath = filePath  + pptname + "_";
            String           file = newimgPath + (i + 1) + ".png";
            FileOutputStream out  = new FileOutputStream(file);
            javax.imageio.ImageIO.write(img, "png", out);
            IOUtils.closeQuietly(out);
        }

    }

}
