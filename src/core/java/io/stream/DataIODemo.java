package core.java.io.stream;

import java.io.*;

public class DataIODemo {

    public static void main(String[] args) throws IOException {
        File file = new File("src/writer.bat");
        if (!file.exists()) {
            file.createNewFile();
        }
        try (var dos = new DataOutputStream(new FileOutputStream(file));
             var dis = new DataInputStream(new FileInputStream(file))
        ) {
            dos.writeInt(1234);
            dos.writeDouble(1234.111);
            dos.writeUTF("哈哈哈");

            System.out.println(dis.readInt());
            System.out.println(dis.readDouble());
            System.out.println(dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
