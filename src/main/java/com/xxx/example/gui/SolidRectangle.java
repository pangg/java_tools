package com.xxx.example.gui;

import javax.swing.*;
import java.awt.*;

/**
 * 使用Graphics类的fillRect()方法显示一个实心矩形
 */
public class SolidRectangle extends JPanel {
    public static void main(String[] a) {
        JFrame f = new JFrame();
        f.setTitle("实心的矩形");
        f.setSize(400, 400);
        f.add(new SolidRectangle());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void paint(Graphics g) {
        g.fillRect(5, 15, 150, 175);
    }
}
