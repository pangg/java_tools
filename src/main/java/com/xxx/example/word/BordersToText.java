package com.xxx.example.word;

import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 对Word文档中的文本应用边框
 */
public class BordersToText {
    public static void main(String[] args)throws Exception {

        //Blank Document
        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(
                new File("/tmp/word/applyingborder.docx"));

        //create paragraph
        XWPFParagraph paragraph = document.createParagraph();

        //Set bottom border to paragraph
        paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);

        //Set left border to paragraph
        paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);

        //Set right border to paragraph
        paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);

        //Set top border to paragraph
        paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);

        XWPFRun run = paragraph.createRun();
        run.setText("易百教程(www.yiibai.com)是因特网上最大的 IT技术学习和开发者资源，其中包括全面的教程、完善的参考手册以及庞大的代码库。易百教程每月接受成千上万的用户访问，并产生几十万以上的页面浏览量。"
        );
        document.write(out);
        out.close();

        System.out.println("applyingborder.docx written successully");
    }
}
