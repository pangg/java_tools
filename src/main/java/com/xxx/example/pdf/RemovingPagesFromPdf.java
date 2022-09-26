package com.xxx.example.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * 从PDF文档中删除文档页
 */
public class RemovingPagesFromPdf {
    public static void main(String args[]) throws IOException {

        //Loading an existing document
        File file = new File("/tmp/pdf/BlankPdf-2.pdf");
        PDDocument doc = PDDocument.load(file);

        //Listing the number of existing pages
        //System.out.print(doc.getNumberOfPages());

        for(int i = 0; i<5; i++){

            //removing the pages
            doc.removePage(i);
        }
        System.out.println("5 pages removed");

        //Saving the document
        doc.save("/tmp/pdf/BlankPdf-2.pdf");

        //Closing the document
        doc.close();
    }
}
