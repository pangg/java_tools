package com.xxx.example.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用ServerSocket类的server.accept()方法和Socket类的sock.getInetAddress()方法来允许连接到套接字端口：`1234
 */
public class ConnectingSocket {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(12345);
            while (true) {
                System.out.println("Listening");
                Socket sock = server.accept();
                InetAddress addr = sock.getInetAddress();
                System.out.println("Connection made to " + addr.getHostName() + " (" + addr.getHostAddress() + ")");
                pause(5000);
                sock.close();
            }
        } catch (IOException e) {
            System.out.println("Exception detected: " + e);
        }
    }

    private static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
