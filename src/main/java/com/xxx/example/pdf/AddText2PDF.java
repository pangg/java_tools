package com.xxx.example.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

/**
 * 向PDF页面中添加文本
 */
public class AddText2PDF {
    public static void main(String args[]) throws IOException {
        String filename = "/tmp/pdf/BlankPdf-3.pdf";
        // Creating PDF document object
        PDDocument document = new PDDocument();
        float version = document.getVersion();
        System.out.println("Version="+version);

        // Add an empty page to it
        document.addPage(new PDPage());

        // Saving the document
        document.save(filename);
        // Loading an existing document
        PDDocument doc = PDDocument.load(new File(filename));

        // Creating a PDF Document
        PDPage page = doc.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        // Begin the Content stream
        contentStream.beginText();

        // Setting the font to the Content stream
        contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );

        // Setting the position for the line
        contentStream.newLineAtOffset(25, 725);
        String text = "This is an example of adding text to a page in the pdf document.";

        // Adding text in the form of string
        contentStream.showText(text);

        // Ending the content stream
        contentStream.endText();
        System.out.println("Content added");

        // Closing the content stream
        contentStream.close();

        // Saving the document
        doc.save(new File(filename));

        // Closing the document
        doc.close();

        System.out.println("All jobs done.");
    }
}
