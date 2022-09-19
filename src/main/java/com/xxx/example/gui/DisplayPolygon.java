package com.xxx.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 创建Polygon()对象来绘制多边形。addPoint()和drawPolygon()方法用于绘制多边形
 */
public class DisplayPolygon extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon p = new Polygon();
        for (int i = 0; i < 5; i++)
            p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 5)),
                    (int) (100 + 50 * Math.sin(i * 2 * Math.PI / 5)));
        g.drawPolygon(p);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Polygon");
        frame.setSize(350, 250);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.YELLOW);
        contentPane.add(new DisplayPolygon());
        frame.setVisible(true);
    }
}
