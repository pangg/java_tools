package com.xxx.example.gui;

import java.awt.*;

public class CreateFrame2 {
    public static void main(String[] args) {
        Frame f = new Frame("标题");
        Component text = new TextArea("One of Best IT Tutorails Website.\nYes/No");
        Component button = new Button("一个按钮");
        f.add(text, BorderLayout.NORTH);
        f.add(button, BorderLayout.SOUTH);
        int width = 300;
        int height = 300;
        f.setSize(width, height);
        f.setVisible(true);
    }
}
