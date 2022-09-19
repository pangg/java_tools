package com.xxx.example.gui;

import javax.swing.*;
import java.awt.*;

public class SolidRectangle2 extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(31, 21, 1));
        g2d.fillRect(250, 195, 90, 60);
    }
    public static void main(String[] args) {
        SolidRectangle2 rects = new SolidRectangle2();
        JFrame frame = new JFrame("绘制实心矩形");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(rects);
        frame.setSize(360, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
