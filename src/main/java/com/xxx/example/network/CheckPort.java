package com.xxx.example.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 通过创建 Socket 对象来检查主机上打开或正在使用的端口(相当于一个简单的端口扫描器)
 */
public class CheckPort {
    public static void main(String[] args) {
        Socket Skt;
        String host = "localhost";

        if (args.length > 0) {
            host = args[0];
        }
        for (int i = 100; i < 1024; i++) {
            try {
                System.out.println("Looking for " + i);
                Skt = new Socket(host, i);
                System.out.println("There is a server on port " + i + " of " + host);
            } catch (UnknownHostException e) {
                System.out.println("Exception occured" + e);
                break;
            } catch (IOException e) {
            }
        }
    }
}
