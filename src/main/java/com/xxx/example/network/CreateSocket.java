package com.xxx.example.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 演示了Socket类的Socket构造函数，并且使用getLocalPort(),getLocalAddress()，getInetAddress()以及getPort()方法获取Socket细节
 */
public class CreateSocket {
    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getByName("112.124.103.85");
            Socket theSocket = new Socket(addr, 80);
            System.out.println("Connected to " + theSocket.getInetAddress() + " on port " + theSocket.getPort()
                    + " from port " + theSocket.getLocalPort() + " of " + theSocket.getLocalAddress());
        } catch (UnknownHostException e) {
            System.err.println("I can't find " + e);
        } catch (SocketException e) {
            System.err.println("Could not connect to " + e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
