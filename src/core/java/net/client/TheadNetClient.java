package core.java.net.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TheadNetClient {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            int i = 1;
            while (true) {
                //每有一个客户端连接到该端口，就创建一个线程处理该客户端的消息
                Socket accept = serverSocket.accept();
                System.out.println("Spawning " + i);
                Runnable runnable = new ThreadedEchoHandler(accept);
                Thread thread = new Thread(runnable);
                thread.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ThreadedEchoHandler implements Runnable {

        private Socket socket;

        public ThreadedEchoHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream();
                 Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
                 PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true)
            ) {
                printWriter.println("Hello! Enter BYE to exit.");
                boolean done = false;
                while (!done && scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    printWriter.println("Echo: " + line);
                    if (line.trim().equals("BYE")) {
                        done = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}