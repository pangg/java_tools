package com.xxx.example.gui;

import javax.swing.*;
import java.awt.*;

public class DisplayTextInRectangle2 {
    public static void main(String[] a) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 300, 300);
        window.getContentPane().add(new MyCanvas());
        window.setVisible(true);
    }
}

class MyCanvas extends JComponent {
    String s = "This is a message.";
    int x = 45;
    int y = 45;
    public void paint(Graphics g) {
        g.drawRect (10, 10, 200, 200);
        g.setColor(Color.blue);
        g.drawString(s, x, y);
    }
}