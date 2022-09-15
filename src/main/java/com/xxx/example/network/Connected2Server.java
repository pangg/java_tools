package com.xxx.example.network;

import java.net.InetAddress;
import java.net.Socket;

/**
 * 使用net.Socket类的sock.getInetAddress()方法与Web服务器连接
 */
public class Connected2Server {
    public static void main(String[] args) {
        try {
            InetAddress addr;
            Socket sock = new Socket("www.yiibai.com", 80);
            addr = sock.getInetAddress();
            System.out.println("Connected to " + addr);
            sock.close();
        } catch (java.io.IOException e) {
            System.out.println("Can't connect to " + args[0]);
            System.out.println(e);
        }
    }
}
