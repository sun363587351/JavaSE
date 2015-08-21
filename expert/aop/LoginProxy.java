package expert.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by IFT8
 * on 2015/8/18.
 */
public class LoginProxy implements MethodInterceptor {
    private Object target;
    private List<Interceptor> interceptors;

    public LoginProxy(Object target, List<Interceptor> interceptors) {
        this.target = target;
        this.interceptors = interceptors;
    }

    /**
     * 创建代理对象
     * @return 代理对象的子类
     */
    public Object creatProxy(){
        Enhancer enhancer=new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(target.getClass());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //链状拦截器 遍历执行完其中方法
        for (Interceptor interceptor : interceptors) {
            interceptor.intercep();
        }
//      执行代理对象方法
        return method.invoke(this.target, objects);
    }
}
