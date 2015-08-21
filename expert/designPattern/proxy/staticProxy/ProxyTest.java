package expert.designPattern.proxy.staticProxy;

import org.testng.annotations.Test;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class ProxyTest {
    @Test
    public void test() {
        Dao dao = new DaoProxy(new DaoImlp(), new Transaction());
        dao.add();
        dao.del();
        dao.update();
        dao.read();
    }
}
