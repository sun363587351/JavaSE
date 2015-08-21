package expert.designPattern.proxy.dynamicProxy.cglibProxy;

import org.testng.annotations.Test;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class ProxyTest {
    @Test
    public void test() {
        DaoProxy daoProxy = new DaoProxy(new Dao(), new Transaction());

        //可以不用接口 返回的是子类
        Dao dao = (Dao) daoProxy.creatProxy();

        dao.add();
        dao.del();
        dao.update();
        dao.read();
    }
}
