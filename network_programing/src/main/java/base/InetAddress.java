package base;

import java.net.UnknownHostException;

/**
 *  InetAdress 封装域名与Ip地址相关的操作
 */

public class InetAddress {

    public static void main(String[] args) throws UnknownHostException {
        java.net.InetAddress inetAddress = java.net.InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress);

        inetAddress = java.net.InetAddress.getByName("localhost");
        System.out.println(inetAddress);

        inetAddress = java.net.InetAddress.getLocalHost();
        System.out.println(inetAddress);
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());
    }

}
