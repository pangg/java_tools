package com.xxx.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFileDemo {
    public static void main(String[] args) {
        String path = "./src/main/resources/test/";
        String name = "111.jpeg";

        try {
            FileInputStream fis = new FileInputStream(path + name);
            FileOutputStream fos = new FileOutputStream(path + "copy" + name);

            int n = 0;
            byte[] b = new byte[1024];
            while ((n = fis.read(b)) != -1) {
                fos.write(b, 0, n);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
