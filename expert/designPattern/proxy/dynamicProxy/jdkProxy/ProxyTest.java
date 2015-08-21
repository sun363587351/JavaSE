package expert.designPattern.proxy.dynamicProxy.jdkProxy;

import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class ProxyTest {
    @Test
    public void test() {
        Dao dao1 = new DaoImlp();
        Transaction transaction=new Transaction();
        DaoProxy daoProxy = new DaoProxy(dao1,transaction);

        //返回的是接口对象是实例
        Dao dao = (Dao)
                Proxy.newProxyInstance(dao1.getClass().getClassLoader(), dao1.getClass().getInterfaces(), daoProxy);

        dao.add();
        dao.del();
        dao.update();
        dao.read();
    }

    @Test
    public void simpleTest() {
        final DaoImlp daoImlp = new DaoImlp();
        final Transaction transaction = new Transaction();
        //返回的是接口对象是实例
        Dao dao = (Dao)
                Proxy.newProxyInstance(daoImlp.getClass().getClassLoader(), daoImlp.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //内部类使用外部类对象 外部类必须为final
                        transaction.beginTransation();

                        method.invoke(daoImlp, args);

                        transaction.commit();
                        return null;
                    }
                });
        dao.read();
        dao.del();
        dao.update();
        dao.read();
    }
}
