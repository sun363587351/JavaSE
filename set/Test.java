package set;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IFT8
 * on 2015/4/3.
 */
public class Test {
    public static void main(String[] args) {
        User user1 = new User("name1", "pwd1", "niName1");
        User user2 = new User("name2", "pwd2", "niName2");
        User user3 = new User("name3", "pwd3", "niName3");
        User user4 = new User("name4", "pwd4", "niName4");
        User user5 = new User("name5", "pwd5", "niName5");
        User user6 = new User("name1", "pwd1", "niName1");
        User user7 = new User("name2", "pwd2", "niName2");
        User user8 = new User("name3", "pwd3", "niName3");
        User user9 = new User("name4", "pwd4", "niName4");
        User user10 = new User("name5", "pwd5", "niName5");
        Set<User> set = new HashSet<User>();
        set.add(user1);
        set.add(user2);
        set.add(user3);
        set.add(user4);
        set.add(user5);
        set.add(user6);
        set.add(user7);
        set.add(user8);
        set.add(user9);
        set.add(user10);
        for (User user : set) {
            show(user);
        }

    }

    public static void show(User user) {
        System.out.println(user.getUserName() + " : " + user.getPassword() + " : " + user.getNikeName());
    }
}
