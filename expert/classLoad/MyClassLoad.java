package expert.classLoad;

import java.io.*;

/**
 * Created with ExpertTest.ClassLoad.
 * User: IFT8
 * Date: 2014/10/16 15:05
 */
public class MyClassLoad extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(name);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            //解密
            ClassCypher.cypher(fin, bout);
            byte[] bytes = bout.toByteArray();
            return defineClass(null, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fin != null)
            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return super.findClass(name);
    }
}
