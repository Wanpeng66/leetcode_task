package com.wp.pdf;

import cn.hutool.core.io.IoUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.print.attribute.ResolutionSyntax.DPI;

/**
 * @author: wp
 * @Title: PdfTask
 * @Description: TODO
 * @date 2022/3/23 15:35
 */
public class PdfTask {
    public static void main( String[] args ) throws IOException {
        File file = new File("D:\\wp\\tmp\\file\\test.pdf");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = IoUtil.readBytes( fileInputStream );
        List<File> list = pdfToImage( bytes,file.getAbsolutePath() );
        System.out.println("................");
    }


    public static List<File> pdfToImage( byte[] fileContent, String path ) throws IOException {
        List<File> result = new ArrayList<>();
        try (PDDocument document = PDDocument.load(fileContent)) {
            PDFRenderer renderer = new PDFRenderer(document);
            for (int i = 0; i < document.getNumberOfPages(); ++i) {
                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, DPI);
                File dstFile = new File(path.replace( ".pdf","" )+"_"+i+".png");
                if (dstFile.exists()) {
                    dstFile.createNewFile();
                }
                ImageIO.write(bufferedImage, "png", dstFile);
                result.add( i,dstFile );
            }
        }
        return result;
    }
}
