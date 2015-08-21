package expert.designPattern.template;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public abstract class RuntimeCalc {
    public abstract void runcode();
    public void calcTime(){
        Long start=System.currentTimeMillis();
        runcode();
        Long end=System.currentTimeMillis();
        System.out.println("time: "+(end-start));
    }
}
