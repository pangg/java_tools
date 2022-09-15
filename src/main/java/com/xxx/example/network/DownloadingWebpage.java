package com.xxx.example.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 使用net.URL类的URL()构造函数来读取和下载网页
 */
public class DownloadingWebpage {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://www.yiibai.com");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter("save2yiibai-index.html"));
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }

}
