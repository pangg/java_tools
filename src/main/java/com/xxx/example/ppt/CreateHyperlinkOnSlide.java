package com.xxx.example.ppt;

import org.apache.poi.xslf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 在PPT中的幻灯片上创建超链接
 */
public class CreateHyperlinkOnSlide {
    public static void main(String args[]) throws IOException {

        // create an empty presentation
        XMLSlideShow ppt = new XMLSlideShow();

        // getting the slide master object
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
        XSLFTextRun textRun = body.addNewTextParagraph().addNewTextRun();

        // setting the text
        textRun.setText("易百教程网");

        // creating the hyperlink
        XSLFHyperlink link = textRun.createHyperlink();

        // setting the link address
        link.setAddress("http://www.yiibai.com/");

        // create the file object
        File file = new File("/tmp/ppt/hyperlink.pptx");
        FileOutputStream out = new FileOutputStream(file);

        // save the changes in a file
        ppt.write(out);
        System.out.println("slide cretated successfully");
        out.close();
    }
}
