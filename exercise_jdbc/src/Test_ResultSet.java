import java.sql.*;

/**
 * Created by 11981 on 2016/12/4.
 */
public class Test_ResultSet {
    // JDBC 驱动器名称 和数据库地址
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String DB_URL = "jdbc:mysql://localhost/shiyanloudb?" +
            "useUnicode=true&characterEncoding=utf-8&useSSL=false";
    //加这一行可以消除一个warning
    //warning:建立ssl连接，但是服务器没有身份认证，这种方式不推荐使用

    //  数据库用户和密码
    static final String USER = "root";

    static final String PASSWORD = "root";

    public static void main(String[] args){
        Connection conn = null;
        Statement stat = null;
        try {
            //注册JDBC 驱动程序
            Class.forName("com.mysql.jdbc.Driver");

            //打开连接
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);


            System.out.println("Creating statement...");
            //创建所需的ResultSet，双向，只读
            stat = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            String sql;
            sql = "SELECT id, name, age FROM Students";
            ResultSet rs = stat.executeQuery(sql);

            System.out.println("Moving cursor to the last");
            rs.last();


            System.out.println("Displaying record...");
            int id = rs.getInt("id");
            int age = rs.getInt("age");
            String name = rs.getString("name");

            //显示
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", Name: " + name);
            System.out.println();

            System.out.println("Moving cursor to the first row..." );
            rs.first();

            System.out.println("Displaying record...");
            id = rs.getInt("id");
            age = rs.getInt("age");
            name = rs.getString("name");

            //显示
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", Name: " + name);
            System.out.println();

            System.out.println("Moving cursor to next row...");
            rs.next();

            System.out.println("Displaying record...");
            id = rs.getInt("id");
            age = rs.getInt("age");
            name = rs.getString("name");

            //显示
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", Name: " + name);
            System.out.println();

            //清理环境
            rs.close();
            stat.close();
            conn.close();
        }catch (SQLException se){
            // JDBC 操作错误
            se.printStackTrace();
            // Class.forName 错误
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //这里一般用来关闭资源的
            try {
                if(stat != null)
                    stat.close();
            }catch (SQLException se2){
            }


            try {
                if(conn != null)
                    conn.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbey!");
    }
}

//        运行结果：
//        Connecting to database...
//        Creating statement...
//        Moving cursor to the last
//        Displaying record...
//        ID: 4, Age: 20, Name: Tomson
//        Moving cursor to the first row...
//        Displaying record...
//        ID: 1, Age: 230, Name: Tom
//        Moving cursor to next row...
//        Displaying record...
//        ID: 2, Age: 23, Name: Aby
//        Goodbey