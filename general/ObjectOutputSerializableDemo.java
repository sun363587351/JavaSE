package general;

import java.io.*;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/10/4 1:13
 */
public class ObjectOutputSerializableDemo {
    public static void main(String[] args) {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("D:\\o.object"));
            oos.writeObject(new Person("Lilin", 18, "cn"));
            oos.flush();
            ois = new ObjectInputStream(new FileInputStream("D:\\o.object"));
            System.out.println(ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (ois != null) try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Person implements Serializable {
    private static final long serialVersionUID = 13L;
    private String name;
    private transient int age = 2;//禁止序列化标识
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    Person(String name, int age, String country) {
        setName(name);
        setAge(age);
        setCountry(country);
    }
    public String toString() {
        return "name:" + this.name + "|age:" + this.age+"|country:"+this.country;
    }
}