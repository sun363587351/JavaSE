package general;

import java.io.*;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/10/5 13:34
 */
public class DataIOStreamDemo {
    public static void main(String[] args) throws IOException {
        write();
        read();
    }

    public static void read() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("D:\\dis.txt"));
        System.out.println(dis.readUTF());
        System.out.println(dis.readUTF());
        dis.close();
    }

    public static void write() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:\\dis.txt"));
        dos.writeUTF("测1℃呃");
        dos.writeUTF("non萨达o");
        dos.close();
    }
}
