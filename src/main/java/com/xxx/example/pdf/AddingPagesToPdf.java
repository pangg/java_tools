package com.xxx.example.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;

/**
 * 使用Java向PDF文档添加页面的示例程序
 */
public class AddingPagesToPdf {
    public static void main(String args[]) throws IOException {

        // Creating PDF document object
        PDDocument document = new PDDocument();

        //File file = new File("/tmp/pdf/BlankPdf.pdf");
        //PDDocument.load(file);

        for (int i = 0; i < 10; i++) {
            // Creating a blank page
            PDPage blankPage = new PDPage();
            // Adding the blank page to the document
            document.addPage(blankPage);
        }
        // Saving the document
        document.save("/tmp/pdf/BlankPdf-2.pdf");
        System.out.println("PDF created");

        // Closing the document
        document.close();
        System.out.println("Yes, All jobs done.");
    }
}
