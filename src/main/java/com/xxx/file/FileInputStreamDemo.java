package com.xxx.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 从文件系统中的某个文件中获得输入字节
 * 用于读取诸如图像数据之类的原始字节流
 */
public class FileInputStreamDemo {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
             fis = new FileInputStream("./src/main/resources/test/score.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fis != null) {
            // readDemo(fis);
            readByteDemo(fis);
        }
    }

    /**
     * read(byte[] b)
     * read(byte[] b, int off, int len)
     * @param fis
     */
    public static void readByteDemo(FileInputStream fis) {
        byte[] b = new byte[100];
        try {
            fis.read(b, 0, 5);
            System.out.println(new String(b));
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read()
     * @param fis
     */
    public static void readDemo(FileInputStream fis) {
        try {
            int n = 0;
            while ((n = fis.read()) != -1) {
                System.out.print((char) n );
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
