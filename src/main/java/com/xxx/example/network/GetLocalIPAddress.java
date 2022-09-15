package com.xxx.example.network;

import java.net.InetAddress;

/**
 * 使用InetAddress类的getLocalAddress()方法获取系统的本地IP地址和主机名
 */
public class GetLocalIPAddress {
    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("Local HostAddress: " + addr.getHostAddress());
        String hostname = addr.getHostName();
        System.out.println("Local host name: " + hostname);
    }
}
