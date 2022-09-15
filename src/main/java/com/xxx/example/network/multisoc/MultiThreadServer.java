package com.xxx.example.network.multisoc;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用ServerSocket类的MultiThreadServer(socketname)方法和Socket类的ssock.accept()方法来创建多线程服务器
 */
public class MultiThreadServer implements Runnable{
    Socket csocket;

    public MultiThreadServer(Socket csocket) {
        this.csocket = csocket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket ssock = new ServerSocket(8806);
        System.out.println("Server Start and Listening: ");

        while (true) {
            Socket sock = ssock.accept();
            System.out.println("Connected");
            new Thread(new MultiThreadServer(sock)).start();
        }
    }

    public void run() {
        try {
            PrintStream pstream = new PrintStream(csocket.getOutputStream());
            for (int i = 100; i > 0; i--) {
                pstream.println(i + "bottles of beer on the wall");
            }
            pstream.close();
            csocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
