package core.java.net.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HalfCloseClient {

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(8189)) {
            try (Socket accept = ss.accept()) {
                InputStream inputStream = accept.getInputStream();
                try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                    PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
                    printWriter.println("Hello! Enter BYE to exit.");
                    //冲刷流
                    printWriter.flush();
                    //关闭输出流
                    accept.shutdownOutput();
                    //以下方法都不再与客户端交互
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
