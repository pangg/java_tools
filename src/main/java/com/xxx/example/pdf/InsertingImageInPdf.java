package com.xxx.example.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;

/**
 * 使用Java向PDF文档页面中添加图片
 */
public class InsertingImageInPdf {
    public static void main(String args[]) throws Exception {
        String filename = "/tmp/pdf/BlankPdf-3.pdf";
        PDDocument document = new PDDocument();
        // Add an empty page to it
        document.addPage(new PDPage());

        // Saving the document
        document.save(filename);
        // Loading an existing document
        File file = new File(filename);
        PDDocument doc = PDDocument.load(file);

        // Retrieving the page
        PDPage page = doc.getPage(0);

        // Creating PDImageXObject object
        PDImageXObject pdImage = PDImageXObject.createFromFile("/tmp/pdf/j2ee.png", doc);

        // creating the PDPageContentStream object
        PDPageContentStream contents = new PDPageContentStream(doc, page);

        // Drawing the image in the PDF document
        contents.drawImage(pdImage, 70, 250);
        System.out.println("Image inserted");

        // Closing the PDPageContentStream object
        contents.close();

        // Saving the document
        doc.save(filename);

        // Closing the document
        doc.close();
    }
}
