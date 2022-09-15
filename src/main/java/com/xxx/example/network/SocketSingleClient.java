package com.xxx.example.network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用Socket类的ssock.accept()方法向单个套接字客户端上显示消息
 */
public class SocketSingleClient {
    public static void main(String[] args) throws IOException {
        ServerSocket ssock = new ServerSocket(8806);
        System.out.println("Listening : ");
        Socket sock = ssock.accept();
        // ssock.close();
        PrintStream ps = new PrintStream(sock.getOutputStream());

        for (int i = 10; i >= 0; i--) {
            ps.println(i + " from Java Source and Support.");
        }
        ps.close();
        sock.close();
    }
}
