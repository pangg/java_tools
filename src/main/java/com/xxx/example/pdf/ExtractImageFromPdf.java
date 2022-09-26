package com.xxx.example.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 将PDF文档生成图片
 */
public class ExtractImageFromPdf {
    public static void main(String args[]) throws Exception {
        String workpath = "/tmp/pdf/";
        // Loading an existing PDF document
        File file = new File(workpath + "t.pdf");
        PDDocument document = PDDocument.load(file);

        // Instantiating the PDFRenderer class
        PDFRenderer renderer = new PDFRenderer(document);

        // Rendering an image from the PDF document
        BufferedImage image = renderer.renderImage(0);

        // Writing the image to a file
        ImageIO.write(image, "JPEG", new File(workpath+"img.png"));
        System.out.println("Image created");

        // Closing the document
        document.close();
    }
}
