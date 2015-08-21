package jdbc;

import java.sql.*;

/**
 * Created by IFT8
 * on 2015/3/28.
 */
public class JDBCDemo {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //1.创建驱动
        try {
            // DriverManager.deregisterDriver(new Driver());这样会创建两次驱动 Driver源码中得出 静态代码块初始化加载一次
            // 而且绑定了代码，不方便复用 所以使用反射
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动包!");
        }
        try {
            //2.创建连接
            connection = DriverManager.getConnection("jdbc:mysql:///ttttt", "Is", "zxc");
            //3.获取传输器
            statement = connection.createStatement();
            //4.获取查询结果
            resultSet = statement.executeQuery("SELECT * FROM user");
            //5.遍历查询结果
            while (resultSet.next()) {
                int math = resultSet.getInt("math");
                System.out.println(math);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            //判断是否存在
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    //如果关闭失败，引用指空可以使垃圾回收器回收
                    resultSet = null;
                }
            }
            //判断是否存在
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    //如果关闭失败，引用指空可以使垃圾回收器回收
                    statement = null;
                }
            }            //判断是否存在
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    //如果关闭失败，引用指空可以使垃圾回收器回收
                    connection = null;
                }
            }
        }
    }
}
