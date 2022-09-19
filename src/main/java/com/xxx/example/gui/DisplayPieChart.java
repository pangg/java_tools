package com.xxx.example.gui;

import javax.swing.*;
import java.awt.*;

class Slice {
    double value;
    Color color;

    public Slice(double value, Color color) {
        this.value = value;
        this.color = color;
    }
}

class MyComponent2 extends JComponent {
    Slice[] slices = {
            new Slice(5, Color.black),
            new Slice(33, Color.green),
            new Slice(20, Color.yellow),
            new Slice(15, Color.red)
    };

    MyComponent2() {
    }

    public void paint(Graphics g) {
        drawPie((Graphics2D) g, getBounds(), slices);
    }

    void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
        double total = 0.0D;

        for (int i = 0; i < slices.length; i++) {
            total += slices[i].value;
        }
        double curValue = 0.0D;
        int startAngle = 0;
        for (int i = 0; i < slices.length; i++) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slices[i].value * 360 / total);
            g.setColor(slices[i].color);
            g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
            curValue += slices[i].value;
        }
    }
}

public class DisplayPieChart {
    public static void main(String[] argv) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MyComponent2());
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}

