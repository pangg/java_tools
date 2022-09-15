package com.xxx.example.network;

import java.net.InetAddress;

/**
 * 通过net.InetAddress类的InetAddress.getByName()方法将指定的IP地址查到主机名称
 */
public class HostSpecificByIP {
    public static void main(String[] argv) throws Exception {
        InetAddress addr = InetAddress.getByName("www.yiibai.com");
        System.out.println("Host name is: "+addr.getHostName());
        System.out.println("Ip address is: "+ addr.getHostAddress());

        System.out.println("Your current IP address : " + InetAddress.getLocalHost());
        System.out.println("Your current Hostname : " + InetAddress.getLocalHost().getHostName());


    }
}
