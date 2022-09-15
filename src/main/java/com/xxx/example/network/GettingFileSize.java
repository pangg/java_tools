package com.xxx.example.network;

import java.net.URL;
import java.net.URLConnection;

/**
 * 从服务器获取文件大小
 */
public class GettingFileSize {
    public static void main(String[] argv) throws Exception {
        int size;
        URL url = new URL("http://www.yiibai.com/downloads/host.txt");
        URLConnection conn = url.openConnection();
        size = conn.getContentLength();

        if (size < 0)
            System.out.println("Could not determine file size.");
        else
            System.out.println("The size of file is = " + size + " bytes");
        conn.getInputStream().close();
    }
}
