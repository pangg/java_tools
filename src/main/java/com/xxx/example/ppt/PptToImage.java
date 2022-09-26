package com.xxx.example.ppt;


import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 将PPT的幻灯片转换为图片
 */
public class PptToImage {
    public static void main(String args[]) throws IOException {
        // creating an empty presentation
        File file = new File("/tmp/ppt/FormatedText.pptx");
        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));

        // getting the dimensions and size of the slide
        Dimension pgsize = ppt.getPageSize();
        List<XSLFSlide> slide = ppt.getSlides();
        BufferedImage img = null;

        for (int i = 0; i < slide.size(); i++) {
            img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();

            // clear the drawing area
            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

            // render
            if (slide.get(i) != null) {
                slide.get(i).draw(graphics);
            }
        }
        // creating an image file as output
        FileOutputStream out = new FileOutputStream("/tmp/ppt/ppt_image.png");

        javax.imageio.ImageIO.write(img, "png", out);
        ppt.write(out);
        System.out.println("Image successfully created");
        out.close();
    }
}
