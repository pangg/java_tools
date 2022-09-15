package com.xxx.example.network;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 检查文件是否在服务器上进行了修改
 */
public class FileModificationTime {
    @Test
    public void test1() throws IOException {
        URL u = new URL("http://www.yiibai.com/static.v4/img/logo.png");
        URLConnection uc = u.openConnection();
        uc.setUseCaches(false);
        long timestamp = uc.getLastModified();
        System.out.println("The last modification time of logo.png is :" + timestamp);
    }

    @Test
    public void test2() throws IOException {
        URL u = new URL("http://www.yiibai.com/favicon.ico");

        URLConnection uc = u.openConnection();
        uc.setUseCaches(false);
        long timestamp = uc.getLastModified();
        System.out.println("The last modification time of file is :"+timestamp);
    }
}
