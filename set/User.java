package set;

/**
 * Created by IFT8
 * on 2015/4/3.
 */
public class User {
    private String userName;
    private String password;
    private String nikeName;

    public User(String userName, String password, String nikeName) {
        this.userName = userName;
        this.password = password;
        this.nikeName = nikeName;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return userName.equals(user.getUserName()) && password.equals(user.getPassword()) && nikeName.equals(user.getNikeName());
    }

    @Override
    public int hashCode() {
        return userName.hashCode()+password.hashCode()+nikeName.hashCode();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }
}
