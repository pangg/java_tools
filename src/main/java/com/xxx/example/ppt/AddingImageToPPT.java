package com.xxx.example.ppt;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 将图片添加到PPT幻灯片中
 */
public class AddingImageToPPT {
    public static void main(String args[]) throws IOException {

        // creating a presentation
        XMLSlideShow ppt = new XMLSlideShow();

        // creating a slide in it
        XSLFSlide slide = ppt.createSlide();

        // reading an image
        File image = new File("/tmp/pdf/img.png");

        // converting it into a byte array
        byte[] picture = IOUtils.toByteArray(new FileInputStream(image));

        // adding the image to the presentation
        XSLFPictureData idx = ppt.addPicture(picture, PictureData.PictureType.PNG);

        // creating a slide with given picture on it
        XSLFPictureShape pic = slide.createPicture(idx);

        // creating a file object
        File file = new File("/tmp/ppt/AddingimageToPPT.pptx");
        FileOutputStream out = new FileOutputStream(file);

        // saving the changes to a file
        ppt.write(out);

        System.out.println("image added successfully");
        out.close();
    }
}
