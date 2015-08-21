package general;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/9/30
 */
public class hashSetSortDemo {
    public static void main(String[] args) {
        Set<String> hashSet = new TreeSet<String>(new MyComparator());
        hashSet.add("_1");
        hashSet.add("_3");
        hashSet.add("_2");
        hashSet.add("_4");
        hashSet.add("_11");
        hashSet.add("_333");
        hashSet.add("_22");
        hashSet.add("_4444");
        for (String s : hashSet) {
            System.out.println(s);
        }
    }
}

class MyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if (o1 != null && o2 != null) {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() > o2.length() ? 1 : -1;
        }
        return 0;
    }
}
