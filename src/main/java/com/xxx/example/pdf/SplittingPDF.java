package com.xxx.example.pdf;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * 将PDF文档页面分割成多个文档
 */
public class SplittingPDF {
    public static void main(String[] args) throws IOException {

        // Creating PDF document object
        PDDocument document = new PDDocument();

        File file = new File("/tmp/pdf/t.pdf");
        PDDocument.load(file);

        /*for (int i = 0; i < 10; i++) {
            // Creating a blank page
            PDPage blankPage = new PDPage();
            // Adding the blank page to the document
            document.addPage(blankPage);
        }
        // Saving the document
        document.save("F:/worksp/javaexamples/java_apache_pdf_box/splitpdf_IP.pdf");
        System.out.println("PDF created");*/

        // Loading an existing PDF document
        File file2 = file;
        PDDocument doc = PDDocument.load(file2);

        // Instantiating Splitter class
        Splitter splitter = new Splitter();

        // splitting the pages of a PDF document
        List<PDDocument> Pages = splitter.split(doc);

        // Creating an iterator
        Iterator<PDDocument> iterator = Pages.listIterator();

        // Saving each page as an individual document
        int i = 1;

        while (iterator.hasNext()) {
            PDDocument pd = iterator.next();
            pd.save("/tmp/pdf/t" + i++ + ".pdf");
        }
        System.out.println("PDF splitted");
    }
}
