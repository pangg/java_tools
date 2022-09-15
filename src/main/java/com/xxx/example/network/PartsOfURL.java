package com.xxx.example.network;

import java.net.URL;

/**
 * 通过net.URL类的url.getProtocol()和url.getFile()方法等获取URL的部分
 */
public class PartsOfURL {
    public static void main(String[] args) throws Exception {
        String webUrl = "http://www.yiibai.com/javaexamples/index.html";
        URL url = new URL(webUrl);
        System.out.println("URL is " + url.toString());
        System.out.println("protocol is " + url.getProtocol());
        System.out.println("file name is " + url.getFile());
        System.out.println("host is " + url.getHost());
        System.out.println("path is " + url.getPath());
        System.out.println("port is " + url.getPort());
        System.out.println("default port is " + url.getDefaultPort());
    }
}
