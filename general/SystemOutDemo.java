package general;

import java.io.*;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/10/2
 */
public class SystemOutDemo {
    public static void main(String[] args) {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String string;
        while (true) {
            try {
                string = br.readLine();
                if ("over".equals(string)) {
                    break;
                }
                bw.write(string);
                bw.newLine();
                bw.flush();
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
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
