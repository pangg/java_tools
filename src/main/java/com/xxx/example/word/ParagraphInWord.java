package com.xxx.example.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 在word文档中写一个段落
 */
public class ParagraphInWord {
    public static void main(String[] args) throws Exception {

        // Blank Document
        XWPFDocument document = new XWPFDocument();

        // Write the Document in file system
        FileOutputStream out = new FileOutputStream("/tmp/word/createparagraph.docx");

        // create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(
                "易百教程(www.yiibai.com) - 易百教程是因特网上最大的 IT技术学习和开发者资源，其中包括全面的教程、完善的参考手册以及庞大的代码库。易百教程每月接受成千上万的用户访问，并产生几十万以上的页面浏览量。");

        document.write(out);
        out.close();
        System.out.println("createparagraph.docx written successfully");
    }
}
