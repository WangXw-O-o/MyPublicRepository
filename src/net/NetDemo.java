package net;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class NetDemo {

    public static void main(String[] args) throws UnknownHostException {
//        socketDemo();
        IPv4Demo();
    }

    public static void socketDemo() {
        try (
                //套接字建立连接
                var s = new Socket("time-a.nist.gov", 13);
                var in = new Scanner(s.getInputStream(), StandardCharsets.UTF_8)
        ) {
            //设置超时时间
            s.setSoTimeout(10000);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取域名对应的地址
     */
    public static void IPv4Demo() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("time-a.nist.gov");
        byte[] addressBytes = address.getAddress();
        System.out.println(Arrays.toString(addressBytes));
        System.out.println(Arrays.toString(InetAddress.getLocalHost().getAddress()));
    }
}
