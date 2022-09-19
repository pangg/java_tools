package com.xxx.example.gui;

import javax.swing.*;
import java.awt.*;

/**
 * 使用RenderingHints类来检查是否打开了抗锯齿
 */
public class CheckAntialiasing {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new MyComponent());
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}

class MyComponent extends JComponent {
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = g2.getRenderingHints();
        boolean bl = rh.containsValue(RenderingHints.VALUE_ANTIALIAS_ON);
        System.out.println(bl);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}