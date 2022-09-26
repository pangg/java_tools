package com.xxx.example.pdf;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * 合并两个PDF文件
 */
public class MergingPdfsIntoOne {
    public static void main(String[] args) throws IOException {
        String workpath = "/tmp/pdf/";
        //Loading an existing PDF document
        File file1 = new File(workpath+"t1.pdf");
        PDDocument doc1 = PDDocument.load(file1);

        File file2 = new File(workpath+"t2.pdf");
        PDDocument doc2 = PDDocument.load(file2);

        //Instantiating PDFMergerUtility class
        PDFMergerUtility PDFmerger = new PDFMergerUtility();

        //Setting the destination file
        PDFmerger.setDestinationFileName(workpath+"merged.pdf");

        //adding the source files
        PDFmerger.addSource(file1);
        PDFmerger.addSource(file2);

        //Merging the two documents
        PDFmerger.mergeDocuments();
        System.out.println("Documents merged");

        //Closing the documents
        doc1.close();
        doc2.close();
    }
}
