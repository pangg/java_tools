package com.xxx.example.network;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通过net.InetAddress类的InetAddress.getByName()方法将主机名更改为指定的IP地址
 */
public class HostSpecificIPAddress {
    public static void main(String[] args) {
        InetAddress address = null;
        try {
            address = InetAddress.getByName("www.yiibai.com");
        } catch (UnknownHostException e) {
            System.exit(2);
        }
        System.out.println(address.getHostName() + " IP is = " + address.getHostAddress());
        System.exit(0);
    }

    /**
     * 使用getHostAddress()和getHostName()获取本机的
     */
    @Test
    public void test2() {
        InetAddress ipadd;
        String hostname;
        try {
            ipadd = InetAddress.getLocalHost();
            hostname = ipadd.getHostName();
            System.out.println("Your IP address : " + ipadd);
            System.out.println("Your Hostname : " + hostname);
        } catch (UnknownHostException e) {
        }
    }
}
