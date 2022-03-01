package com.xxx.file;

import java.io.*;

public class ReaderDemo {
    public static void main(String[] args) {
        String name = "./src/main/resources/test/score.txt";
        String name1 = "./src/main/resources/test/score1.txt";

        try {
            FileInputStream fis = new FileInputStream(name);
            InputStreamReader isr = new InputStreamReader(fis, "GBK");
            BufferedReader br = new BufferedReader(isr);
            FileOutputStream fos = new FileOutputStream(name1);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
            BufferedWriter bw = new BufferedWriter(osw);
            int n = 0;
            char[] cbuf = new char[10];
           /* while ((n = isr.read()) != -1) {
                System.out.print((char) n);
            }*/

            /*while ((n = isr.read(cbuf)) != -1) {
                String s = new String(cbuf, 0, n);
                System.out.print(s);
            }*/

            while ((n = br.read(cbuf)) != -1) {
                // String s = new String(cbuf, 0, n);
                // osw.write(s);
                bw.write(cbuf, 0, n);
                bw.flush();
            }

            fis.close();
            fos.close();
            isr.close();
            osw.close();
            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
