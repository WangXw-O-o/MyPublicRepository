package core.java.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class IODemo {

    public static void main(String[] args) throws IOException {
        var in = new RandomAccessFile("C:\\Users\\wxw\\Desktop\\test.txt", "r");
        long filePointer = in.getFilePointer();
        System.out.println(filePointer);
        long length = in.length();
        System.out.println(length);
        in.seek(1);
        char c = in.readChar();
        System.out.println("c : " + c);
    }

    public static void fileDemo1() throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\wxw\\Desktop\\test.txt"));
        int available = inputStream.available();
        System.out.println(available);
        if (available > 0) {
            byte[] bytes = inputStream.readAllBytes();
            System.out.println(Arrays.toString(bytes));
        }

        FileReader reader = new FileReader(new File("C:\\Users\\wxw\\Desktop\\test.txt"));
        System.out.println("读取一个字符：" + (char) reader.read());

        BufferedReader bf = new BufferedReader(reader);
        System.out.println("读取一行：" + bf.readLine());
        System.out.println("读取一个字符：" + (char) bf.read());
        System.out.println("读取一行：" + bf.readLine());
        System.out.println("读取一个字符：" + (char) bf.read());
        System.out.println("读取一行：" + bf.readLine());

        bf.close();
        reader.close();
        inputStream.close();
    }

}
