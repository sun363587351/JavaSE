package expert.designPattern.proxy.dynamicProxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class DaoProxy implements MethodInterceptor {
    private Dao dao;
    private Transaction transaction;

    public DaoProxy(Dao dao, Transaction transaction) {
        this.dao = dao;
        this.transaction = transaction;
    }

    public Object creatProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(this.dao.getClass());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        this.transaction.beginTransation();
        Object object = method.invoke(this.dao, objects);
        this.transaction.commit();
        return object;
    }
}
