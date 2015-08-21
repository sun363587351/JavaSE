package expert.classLoad;

import java.io.*;

/**
 * Created with ExpertTest.ClassLoad.
 * User: IFT8
 * Date: 2014/10/17 9:30
 */
public class ClassCypher {
    public static void main(String[] args) {
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try {
//            fin = new FileInputStream(args[0]);
//            fout = new FileOutputStream(args[1]);
            fin = new FileInputStream("D:\\\\EncryptedCalss.class");
            fout = new FileOutputStream("D:\\\\EncryptedCalss");
            cypher(fin, fout);
            System.out.println("Encrypted Successful");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fout != null) {
                    try {
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void cypher(InputStream in, OutputStream out) throws IOException {
        int b;
        while ((b = in.read()) != -1) {
            out.write(b ^ 0x13);
        }
    }
}
