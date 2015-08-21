package expert.designPattern.proxy.dynamicProxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class DaoProxy implements InvocationHandler {
    private Dao dao;
    private Transaction transaction;

    public DaoProxy(Dao dao,Transaction transaction) {
        this.dao = dao;
        this.transaction=transaction;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        this.transaction.beginTransation();
        Object object=method.invoke(this.dao, args);
        this.transaction.commit();
        return object;
    }
}
