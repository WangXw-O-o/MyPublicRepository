package core.java.net.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SingleNetClient {

    public static void main(String[] args) {
        try (
                //监听端口8189的服务
                ServerSocket ss = new ServerSocket(8189)
        ) {
            try (
                    //阻塞线程，直到有客户端连接到这个端口，就返回套接字
                    Socket accept = ss.accept()
            ) {
                //套接字中获取输入流（客户端发送过来的数据），输出流（向客户端发送数据）
                InputStream inputStream = accept.getInputStream();
                OutputStream outputStream = accept.getOutputStream();
                try (
                        //使用Scanner与客户端交互
                        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)
                ) {
                    //输出流转换，重点需要设置autoFlush参数为true
                    PrintWriter printWriter = new PrintWriter(
                            new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),
                            true);
                    //向客户端发送数据
                    printWriter.println("Hello! Enter BYE to exit.");
                    boolean done = false;
                    while (!done && scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        printWriter.println("Echo: " + line);
                        if (line.trim().equals("BYE")) {
                            done = true;
                        }
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
