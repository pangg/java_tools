package com.xxx.example.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.IOException;

/**
 * 创建空PDF文档
 */
public class CreatingEmptyPdf {
    public static void main(String args[]) throws IOException {

        // Creating PDF document object
        PDDocument document = new PDDocument();

        // Add an empty page to it
        document.addPage(new PDPage());

        // Saving the document
        document.save("/tmp/pdf/BlankPdf.pdf");
        System.out.println("PDF created");

        // Closing the document
        document.close();
    }
}
