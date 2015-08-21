package expert.designPattern.proxy.dynamicProxy.cglibProxy;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class Transaction {
    public void beginTransation(){
        System.out.println("开始事务");
    }
    public void commit(){
        System.out.println("提交事务并关闭");
    }
}
