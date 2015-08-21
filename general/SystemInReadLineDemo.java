package general;

import java.io.*;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/9/30
 */
public class SystemInReadLineDemo {
    public static void main(String[] args) {
//        readIn();
        readLine();
    }

    public static void readIn() {
        InputStream in = System.in;
        StringBuilder sb = new StringBuilder();
        int ch;
        String string;
        while (true) {
            try {
                ch = in.read();
                if (ch == '\r') {
                    continue;
                }
                if (ch == '\n') {
                    string = sb.toString();
                    if ("over".equals(string)) {
                        break;
                    }
                    System.out.println(string);
                    //delete比直接new 一个效率高
                    sb.delete(0, sb.length());
                } else sb.append((char) ch);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流转字符流读入一行
     */
    public static void readLine() {
//        InputStream in = System.in;
//        InputStreamReader isr = new InputStreamReader(in);
//        BufferedReader br = new BufferedReader(isr);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String string;
        while (true) {
            try {
                string = br.readLine();
                if ("over".equals(string)) {
                    break;
                }
                System.out.println(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //只需要关闭最外围，其余自动关闭，因为修饰类使用传入类自己的方法了
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
