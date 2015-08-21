package general;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/9/30
 */
public class varargsMethodDemo {
    public static void main(String[] args) {
        show("_1");
        show();
        show("_0123456789012345678901234567890", "_02", "_3");
    }

    public static void show(String... strings) {
        int length=3;
        for (String string : strings) {
            System.out.println(string.length() < length ? string : string.substring(0, length));
        }
    }
}
