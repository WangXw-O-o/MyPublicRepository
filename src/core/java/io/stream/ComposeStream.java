package core.java.io.stream;

import java.io.*;
import java.util.Arrays;

public class ComposeStream {

    public static void main(String[] args) throws FileNotFoundException {
//        fileAndData();
        pushBackAndFile();
    }

    //读入数字
    public static void fileAndData() {
        try (FileInputStream fileInputStream = new FileInputStream("src/StreamTest.txt");
             DataInputStream dataInputStream = new DataInputStream(fileInputStream);) {
            var read = dataInputStream.read();
            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pushBackAndFile() {
        try (var pbIn = new PushbackInputStream(
                new BufferedInputStream(
                        new FileInputStream("src/StreamTest.txt")))) {
            int b = pbIn.read();
            System.out.println("读入一个字节，将该字节转换为int表示为：" + b);
            System.out.println("字符'a'是一个码元，其值转换为int表示为："+"a".codePointAt(0));
            if (b == 'a') {
                //推回流中
                pbIn.unread(b);
                System.out.println("push back");
            }
            int c = pbIn.read();
            if (c == 'a') {
                System.out.println(Character.toString(c));
            }
            //Java9之后有readAllBytes
            byte[] bytes = pbIn.readAllBytes();
            for (byte k : bytes) {
                System.out.print(Character.toString(k));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
