package expert.designPattern.proxy.staticProxy;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class DaoImlp implements Dao {
    public void add(){
        System.out.println("Dao add");
    }
    public void del(){
        System.out.println("Dao del");
    }
    public void update(){
        System.out.println("Dao update");
    }
    public void read(){
        System.out.println("Dao read");
    }
}
