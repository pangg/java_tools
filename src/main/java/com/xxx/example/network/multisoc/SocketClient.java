package com.xxx.example.network.multisoc;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketClient {
    public static void main(String[] args) {
        try {
            //InetAddress addr = InetAddress.getByName("127.0.0.1");
            Socket theSocket = new Socket("127.0.0.1", 12345);
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
