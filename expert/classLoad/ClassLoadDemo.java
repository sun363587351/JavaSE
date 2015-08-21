package expert.classLoad;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with ExpertTest.ClassLoad.
 * User: IFT8
 * Date: 2014/10/17 9:50
 */
public class ClassLoadDemo {
    public static void main(String[] args) {
        try {
//            Class<?> clazz = new MyClassLoad().loadClass(args[0]);
            Class<?> clazz = new MyClassLoad().loadClass("D:\\\\EncryptedCalss");
            clazz.getMethod("main", String[].class).invoke(null,new Object[]{null});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
