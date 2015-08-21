package testNG;


import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created with TestNG.
 * User: IFT8
 * Date: 2014/11/5 11:39
 */
public class Demo {
    @Test
    public void test() {
        System.out.println("Hello JUnit,this is test");
    }

    @Test
    @Parameters({"根据这里查找xml配置的数据"})
    //  @Optional()中填入的是测试数据
    //  虽然@Parameters可以随便填写，但是不能少它
    public void show(@Optional("str……")String str) {
        System.out.println(str);
    }
}
