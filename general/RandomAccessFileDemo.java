package general;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/10/4 21:09
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            writeInt(i,i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(readInt(i));
        }
    }

    public static int readInt(int location) {
        int intData = -1;
        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile("D:\\ran.txt", "r");
            randomFile.seek(location * 4);//设置指针 数据为int类型 4字节
            intData = randomFile.readInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null)
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return intData;
    }

    public static void writeInt(int data, int location) {
        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile("D:\\ran.txt", "rw");
            randomFile.seek(location * 4);//设置指针 数据为int类型 4字节
//            randomFile.skipBytes(location*4); //跳过字节数并设置指针
            randomFile.writeInt(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null)
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
