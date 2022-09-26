package com.xxx.example.ppt;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 创建一个空的PPT文档
 */
public class CreatingBlankPPT {
    public static void main(String args[]) throws IOException {

        // creating a new empty slide show
        XMLSlideShow ppt = new XMLSlideShow();

        // creating an FileOutputStream object
        File file = new File("/tmp/ppt/blank_ppt.pptx");
        FileOutputStream out = new FileOutputStream(file);

        // saving the changes to a file
        ppt.write(out);
        System.out.println("Presentation created successfully");
        out.close();
    }
}
