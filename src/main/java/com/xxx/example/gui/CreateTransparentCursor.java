package com.xxx.example.gui;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

/**
 * 使用“invisibleCursor”作为参数的createCustomCursor()方法来创建一个透明光标
 */
public class CreateTransparentCursor {
    public static void main(String[] argv) throws Exception {
        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
        Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0),
                "invisibleCursor");
        System.out.println("Transparent Cursor created.");
    }

    @Test
    public void test1() {
        JFrame frame = new JFrame();
        frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
                new Point(0, 0), "null"));
        frame.setSize(600, 600);
        frame.setVisible(true);


    }
}
