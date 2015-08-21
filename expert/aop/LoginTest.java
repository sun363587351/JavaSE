package expert.aop;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IFT8
 * on 2015/8/18.
 */
public class LoginTest {
    @Test
    public void test() {
        //构造拦截器链
        List<Interceptor> interceptors = new ArrayList<Interceptor>();
        interceptors.add(new Logger());
        interceptors.add(new Privilege());
        //构造代理
        LoginProxy loginProxy = new LoginProxy(new LoginManage(), interceptors);
        //生成代理对象
        LoginManage loginManage = (LoginManage) loginProxy.creatProxy();
        System.out.println(loginManage.showState());
    }
}
