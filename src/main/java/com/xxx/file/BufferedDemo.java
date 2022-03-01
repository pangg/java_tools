package com.xxx.file;

import java.io.*;

/**
 *  缓冲输入流 BufferedInputStream
 *  缓冲输出流 BufferedOutputStream
 */
public class BufferedDemo {
    public static void main(String[] args) {
        String name = "./src/main/resources/test/score1.txt";
        try {
            FileOutputStream fos = new FileOutputStream(name);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            FileInputStream fis = new FileInputStream(name);
            BufferedInputStream bis = new BufferedInputStream(fis);
            long startTime = System.currentTimeMillis();
            bos.write(50);
            bos.write('a');
            bos.flush(); // 缓冲区未写满，调用该方法强制清空缓冲区并写入文件
            System.out.println(bis.read());
            System.out.println((char) bis.read());
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
            fos.close();
            bos.close();  // 也会强制清空缓冲区并写入文件
            fis.close();
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
