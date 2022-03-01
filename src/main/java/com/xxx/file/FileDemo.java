package com.xxx.file;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) {
        File file1 = new File("./src/main/resources/test");
        File file2 = new File("./src/main/resources/test/score.txt");
        System.out.println(file1.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file1.isAbsolute());

        if (! file1.exists()) {
            file1.mkdirs();
        }

        if (! file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
