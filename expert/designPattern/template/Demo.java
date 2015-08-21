package expert.designPattern.template;

import org.testng.annotations.Test;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class Demo extends RuntimeCalc {

    @Override
    public void runcode(){
        for (int i = 0; i < 100000; i++) {
            if(i==hashCode())
                return;
        }
    }

    @Test
    public void test(){
        calcTime();
    }
}
