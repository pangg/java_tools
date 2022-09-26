package com.xxx.example.ppt;

import org.apache.poi.xslf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文本格式化
 */
public class FormatTextPPT {
    public static void main(String args[]) throws IOException {

        // creating an empty presentation
        XMLSlideShow ppt = new XMLSlideShow();

        // getting the slide master object
        // XSLFSlideMaster slideMaster = ppt.getSlideMasters()[0];
        java.util.List<XSLFSlideMaster> sl = ppt.getSlideMasters();
        XSLFSlideMaster slideMaster = sl.get(0);
        // select a layout from specified list
        XSLFSlideLayout slidelayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

        // creating a slide with title and content layout
        XSLFSlide slide = ppt.createSlide(slidelayout);

        // selection of title place holder
        XSLFTextShape body = slide.getPlaceholder(1);

        // clear the existing text in the slide
        body.clearText();

        // adding new paragraph
        XSLFTextParagraph paragraph = body.addNewTextParagraph();

        // formatting line 1
        XSLFTextRun run1 = paragraph.addNewTextRun();
        run1.setText(" 这是红色的一行文本字符串 ");

        // setting color to the text
        run1.setFontColor(java.awt.Color.red);

        // setting font size to the text
        run1.setFontSize(24.00);

        // moving to the next line
        paragraph.addLineBreak();

        // formatting line 2
        XSLFTextRun run2 = paragraph.addNewTextRun();
        run2.setText(" 这是一行加粗的字符串 ");
        run2.setFontColor(java.awt.Color.CYAN);

        // making the text bold
        run2.setBold(true);
        paragraph.addLineBreak();

        // formatting line 3
        XSLFTextRun run3 = paragraph.addNewTextRun();
        run3.setText(" 这是加了划线的字符串");
        run3.setFontSize(12.00);

        // making the text italic
        run3.setItalic(true);

        // strike through the text
        run3.setStrikethrough(true);
        paragraph.addLineBreak();

        // formatting line 4
        XSLFTextRun run4 = paragraph.addNewTextRun();
        run4.setText(" 这是加了下划线的文本 ");
        run4.setUnderlined(true);

        // underlining the text
        paragraph.addLineBreak();

        // creating a file object
        File file = new File("/tmp/ppt/FormatedText.pptx");
        FileOutputStream out = new FileOutputStream(file);

        // saving the changes to a file
        ppt.write(out);
        out.close();
        System.out.println("PPT created");
    }
}
