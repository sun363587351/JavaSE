package general;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/9/30
 */

import static java.util.Arrays.sort;
import static java.lang.System.out;

public class importStaticMethodDemo {
    public static void main(String[] args) {
        String[] strings = {"a", "z", "x", "s", "d"};
        for (String string : strings) {
            //省去System
            out.print(string);
        }

        //不用写Arrays.sort
        sort(strings);
        out.println("\n--Sort--");
        for (String string : strings) {
            //省去System
            out.print(string);
        }
    }
}
