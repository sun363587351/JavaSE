package expert.aop;

/**
 * Created by IFT8
 * on 2015/8/18.
 */
public class Privilege implements Interceptor{
    @Override
    public void intercep() {
        System.out.println("privilege……");
    }
}
