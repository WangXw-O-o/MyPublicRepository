package core.java.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ReaderAndWriterDemo {

    public static void main(String[] args) throws IOException {
        print();
    }

    public static void print() throws IOException {
        String path = "src/writer.txt";
        File file = new File(path);
        if (file.createNewFile()) {
            System.out.println("创建文件成功！");
        } else if (file.exists()) {
            System.out.println("文件已存在！");
        } else {
            System.out.println("文件创建失败！");
        }
        //OutputStreamWriter
        writerByOutputStreamWriter(file);
        //InputStreamReader
        readerByInputStreamReader(file);
        //PrintWriter
        writerByPrintWriter(file);
        //BufferedReader
        readerByBufferedReader(file);
        //Files.readAllLines
        readerByFiles(path);
    }

    public static void writerByOutputStreamWriter(File file) {
        try (
                OutputStreamWriter outWriter =
                        new OutputStreamWriter(
                                new FileOutputStream(file), StandardCharsets.UTF_8)) {
            //写入字符串，本质上是写入一个字符数组
            outWriter.write("12x");
            //本质上写入的是一个字符
            outWriter.write(97);
            outWriter.write('a');
            //无法写入浮点数等其他类型
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writerByPrintWriter(File file) {
        try (
                PrintWriter outWriter =
                        new PrintWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(file), StandardCharsets.UTF_8))) {
            outWriter.println("123456789");
            outWriter.println(97);
            outWriter.println('a');
            outWriter.println((double) 1111);
            outWriter.println(true);
            //print、println方法输出功能强大
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readerByInputStreamReader(File file) {
        try (
                InputStreamReader inReader =
                        new InputStreamReader(
                                new FileInputStream(file), StandardCharsets.UTF_8)) {
            System.out.println("---readerByInputStreamReader: ");
            while (inReader.ready()) {
                int read = inReader.read();
                System.out.println(Character.toString(read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readerByBufferedReader(File file) {
        try (BufferedReader bReader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            System.out.println("---readerByBufferedReader: ");
            while ((line = bReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readerByFiles(String path) throws IOException {
        //读取所有行到List中
        List<String> lineList = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        System.out.println("---readerByFiles：");
        for (String line : lineList) {
            System.out.println(line);
        }
        //文件太大的时候，惰性处理
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            //处理Stream
        }
    }
}
