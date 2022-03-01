package com.xxx.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {
    public static void main(String[] args) {
        String name = "./src/main/resources/test/score1.txt";
        FileOutputStream fos;
        FileInputStream fis;
        try {
            fos = new FileOutputStream(name, true);
            fis = new FileInputStream(name);
            fos.write(50);
            fos.write('a');
            System.out.println(fis.read());
            System.out.println((char) fis.read());
            fos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
