package com.xxx.example.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 创建一个空的Wold文档
 */
public class CreateWordDocument {
    public static void main(String[] args) throws Exception {

        // Blank Document
        XWPFDocument document = new XWPFDocument();

        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("/tmp/word/createdocument.docx"));

        document.write(out);
        out.close();

        System.out.println("createdocument.docx written successully");
    }
}
