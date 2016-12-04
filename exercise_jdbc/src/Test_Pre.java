import java.sql.*;

/**
 * Created by 11981 on 2016/12/4.
 */
public class Test_Pre {

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
        PreparedStatement stat = null;
        try {
            //注册JDBC 驱动程序
            Class.forName("com.mysql.jdbc.Driver");

            //打开连接
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);

            //执行查询
            System.out.println("Creating statement...");
            String sql;
            sql = "UPDATE Students set age=? WHERE id=?";
            stat = conn.prepareStatement(sql);

            stat.setInt(1, 23);
            stat.setInt(2, 2);
            int rows = stat.executeUpdate();
            System.out.println("被影响的行数 ： " + rows);

            sql = "SELECT id, name, age FROM Students";
            ResultSet rs = stat.executeQuery(sql);

            //得到和处理结果集
            while (rs.next()){
                //检索
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");

                //显示
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", Name: " + name);
                System.out.println();
            }

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
