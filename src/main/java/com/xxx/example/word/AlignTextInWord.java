package com.xxx.example.word;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 对齐文档中的文本
 */
public class AlignTextInWord {
    public static void main(String[] args) throws Exception {

        // Blank Document
        XWPFDocument document = new XWPFDocument();

        // Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("/tmp/word/alignparagraph.docx"));

        // create paragraph
        XWPFParagraph paragraph = document.createParagraph();

        // Set alignment paragraph to RIGHT
        paragraph.setAlignment(ParagraphAlignment.RIGHT);

        XWPFRun run = paragraph.createRun();
        run.setText("易百教程(yiibai.com)是因特网上最大的 IT技术学习和开发者资源，其中包括全面的教程、完善的参考手册以及庞大的代码库。"+" 易百教程每月接受成千上万的用户访问，并产生几十万以上的页面浏览量。");

        // Create Another paragraph
        paragraph = document.createParagraph();

        // Set alignment paragraph to CENTER
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        run = paragraph.createRun();
        run.setText("\r\n易百教程把提供高品质的 IT技术学习入门实例教程资源作为自身的使命。\r\n易百教程将为用户提供永久免费的内容和服务。 ");

        document.write(out);
        out.close();

        System.out.println("alignparagraph.docx written successfully");
    }
}
