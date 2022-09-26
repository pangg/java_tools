package com.xxx.example.ppt;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 合并两个PPT文档
 */
public class MergingMultiplePresentations {
    public static void main(String args[]) throws IOException {

        // creating empty presentation
        XMLSlideShow ppt = new XMLSlideShow();

        // taking the two presentations that are to be merged
        String file1 = "/tmp/ppt/FormatedText.pptx";
        String file2 = "/tmp/ppt/hyperlink.pptx";
        String[] inputs = { file1, file2 };

        for (String arg : inputs) {
            FileInputStream inputstream = new FileInputStream(arg);
            XMLSlideShow src = new XMLSlideShow(inputstream);

            for (XSLFSlide srcSlide : src.getSlides()) {

                // merging the contents
                ppt.createSlide().importContent(srcSlide);
            }
        }
        String file3 = "/tmp/ppt/merging.pptx";

        // creating the file object
        FileOutputStream out = new FileOutputStream(file3);

        // saving the changes to a file
        ppt.write(out);
        System.out.println("Merging done successfully");
        out.close();
    }

}
